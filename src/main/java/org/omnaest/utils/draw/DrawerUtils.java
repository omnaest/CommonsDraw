package org.omnaest.utils.draw;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.omnaest.utils.draw.domain.CanvasSize;
import org.omnaest.utils.draw.domain.CanvasSizeProvider;
import org.omnaest.utils.draw.domain.Color;
import org.omnaest.utils.draw.domain.ColorProvider;
import org.omnaest.utils.draw.domain.Distance;
import org.omnaest.utils.draw.domain.DrawProcessor;
import org.omnaest.utils.draw.domain.Drawer;
import org.omnaest.utils.draw.domain.Drawer.PolylineBuilder;
import org.omnaest.utils.draw.domain.DrawerBuilder;
import org.omnaest.utils.draw.domain.Point;
import org.omnaest.utils.draw.internal.DrawProcessorManager;
import org.omnaest.utils.draw.internal.PointTranslator;
import org.omnaest.utils.draw.internal.VectorUtils;

public class DrawerUtils
{
    private static class DrawingContext
    {
        private ColorProvider strokeColor = Color.BLACK;
        private ColorProvider fillColor   = Color.BLACK;
        private double        strokeWidth = 1.0;

        public ColorProvider getStrokeColor()
        {
            return this.strokeColor;
        }

        public void setStrokeColor(ColorProvider currentStrokeColor)
        {
            this.strokeColor = currentStrokeColor;
        }

        public ColorProvider getFillColor()
        {
            return this.fillColor;
        }

        public void setFillColor(ColorProvider currentFillColor)
        {
            this.fillColor = currentFillColor;
        }

        public double getStrokeWidth()
        {
            return this.strokeWidth;
        }

        public void setStrokeWidth(double currentStrokeWidth)
        {
            this.strokeWidth = currentStrokeWidth;
        }

    }

    public static DrawerBuilder builder()
    {
        return new DrawerBuilder() {
            private PointTranslator      pointTranslator      = new PointTranslator();
            private DrawProcessorManager drawProcessorManager = new DrawProcessorManager();

            @Override
            public Drawer build()
            {
                return new Drawer() {
                    private DrawingContext drawingContext = new DrawingContext();

                    @Override
                    public Drawer withStrokeColor(Color color)
                    {
                        return this.withStrokeColor((ColorProvider) color);
                    }

                    @Override
                    public Drawer withStrokeColor(ColorProvider color)
                    {
                        this.drawingContext.setStrokeColor(color);
                        return this;
                    }

                    @Override
                    public Drawer withStrokeWidth(double strokeWidth)
                    {
                        this.drawingContext.setStrokeWidth(strokeWidth);
                        return this;
                    }

                    @Override
                    public Drawer withFillColor(Color color)
                    {
                        return this.withFillColor((ColorProvider) color);
                    }

                    @Override
                    public Drawer withFillColor(ColorProvider color)
                    {
                        this.drawingContext.setFillColor(color);
                        return this;
                    }

                    @Override
                    public Drawer drawPolygon(Consumer<PolygonBuilder> polygonBuilderConsumer)
                    {
                        // TODO Auto-generated method stub
                        return this;
                    }

                    @Override
                    public Drawer drawPolyline(Consumer<PolylineBuilder> polylineBuilderConsumer)
                    {
                        PolylineBuilderImpl polylineBuilder = this.createPolylineBuilder();
                        polylineBuilderConsumer.accept(polylineBuilder);
                        polylineBuilder.apply(drawProcessorManager, pointTranslator, this.drawingContext);
                        return this;
                    }

                    private PolylineBuilderImpl createPolylineBuilder()
                    {
                        return new PolylineBuilderImpl();
                    }

                    @Override
                    public Drawer drawLine(Point from, Point to)
                    {
                        // TODO Auto-generated method stub
                        return this;
                    }

                    @Override
                    public Drawer drawCircle(Point center, double radius)
                    {
                        drawProcessorManager.circle(pointTranslator.apply(center), pointTranslator.apply(radius),
                                                    pointTranslator.apply(this.drawingContext.getStrokeWidth()), this.drawingContext.getStrokeColor(),
                                                    this.drawingContext.getFillColor());
                        return this;
                    }

                    @Override
                    public Drawer drawRectangle(Point from, Point to)
                    {
                        // TODO Auto-generated method stub
                        return null;
                    }

                    @Override
                    public Drawer cursor(Consumer<Cursor> cursorConsumer)
                    {
                        Cursor cursor = this.createCursor();
                        cursorConsumer.accept(cursor);
                        return this;
                    }

                    private Cursor createCursor()
                    {
                        Drawer drawer = this;
                        return new Cursor() {
                            private Point currentPoint = Point.zero();

                            @Override
                            public CursorLine startDrawing()
                            {
                                Cursor cursor = this;

                                PolylineBuilderImpl polylineBuilder = createPolylineBuilder();

                                return new CursorLine() {
                                    @Override
                                    public CursorLine moveTo(Point point)
                                    {
                                        cursor.moveTo(point);
                                        this.addPoint();
                                        return this;
                                    }

                                    private void addPoint()
                                    {
                                        polylineBuilder.add(currentPoint);
                                    }

                                    @Override
                                    public CursorLine move(Distance distance)
                                    {
                                        cursor.move(distance);
                                        this.addPoint();
                                        return this;
                                    }

                                    @Override
                                    public Cursor stopDrawing()
                                    {
                                        polylineBuilder.apply(drawProcessorManager, pointTranslator, drawingContext);
                                        return cursor;
                                    }
                                };
                            }

                            @Override
                            public Cursor moveTo(Point point)
                            {
                                this.currentPoint = point;
                                return this;
                            }

                            @Override
                            public Cursor move(Distance distance)
                            {
                                return this.moveTo(VectorUtils.add(this.currentPoint, distance));
                            }

                            @Override
                            public Cursor drawCircle(double radius)
                            {
                                drawer.drawCircle(this.currentPoint, radius);
                                return this;
                            }
                        };
                    }
                };
            }

            @Override
            public DrawerBuilder withCanvasSize(CanvasSize canvasSize)
            {
                return this.withCanvasSize((CanvasSizeProvider) canvasSize);
            }

            @Override
            public DrawerBuilder withCanvasSize(CanvasSizeProvider canvasSize)
            {
                this.pointTranslator.setCanvasSizeProvider(canvasSize);
                return this;
            }

            @Override
            public DrawerBuilder addDrawProcessor(DrawProcessor drawProcessor)
            {
                this.drawProcessorManager.addProcessor(drawProcessor);
                return this;
            }
        };
    }

    private static class PolylineBuilderImpl implements PolylineBuilder
    {
        private List<Point> points = new ArrayList<>();

        @Override
        public PolylineBuilder add(Point point)
        {
            this.points.add(point);
            return this;
        }

        public void apply(DrawProcessorManager drawProcessorManager, PointTranslator pointTranslator, DrawingContext drawingContext)
        {
            drawProcessorManager.polyline(this.points.stream()
                                                     .map(pointTranslator)
                                                     .collect(Collectors.toList()),
                                          drawingContext.getStrokeColor(), drawingContext.getStrokeWidth());
        }
    }
}
