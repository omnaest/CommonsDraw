package org.omnaest.utils.draw.domain;

import java.util.function.Consumer;

public interface Drawer
{
    public Drawer withStrokeColor(Color colors);

    public Drawer withStrokeColor(ColorProvider color);

    public Drawer withFillColor(ColorProvider color);

    public Drawer withFillColor(Color color);

    public Drawer withStrokeWidth(double width);

    public Drawer drawPolygon(Consumer<PolygonBuilder> polygonBuilderConsumer);

    public Drawer drawPolyline(Consumer<PolylineBuilder> polylineBuilderConsumer);

    public Drawer drawLine(Point from, Point to);

    public Drawer drawCircle(Point center, double radius);

    public Drawer drawRectangle(Point from, Point to);

    public Drawer cursor(Consumer<Cursor> cursorConsumer);

    public static interface PolygonBuilder
    {
        public PolygonBuilder add(Point point);
    }

    public static interface PolylineBuilder
    {
        public PolylineBuilder add(Point point);
    }

    public static interface MoveableCursor<R>
    {
        public R moveTo(Point point);

        public R move(Distance distance);

    }

    public static interface Cursor extends MoveableCursor<Cursor>
    {
        public CursorLine startDrawing();

        public Cursor drawCircle(double radius);
    }

    public static interface CursorLine extends MoveableCursor<CursorLine>
    {
        public Cursor stopDrawing();
    }
}
