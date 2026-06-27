package org.omnaest.utils.draw;

import java.util.List;

import org.junit.Test;
import org.omnaest.utils.draw.domain.Color;
import org.omnaest.utils.draw.domain.ColorProvider;
import org.omnaest.utils.draw.domain.Distance;
import org.omnaest.utils.draw.domain.DrawProcessor;
import org.omnaest.utils.draw.domain.Drawer;
import org.omnaest.utils.draw.domain.Point;

public class DrawerUtilsTest
{
    private Drawer drawer = DrawerUtils.builder()
                                       .addDrawProcessor(this.createDrawProcessor())
                                       .build();

    @Test
    public void testDrawPolygon() throws Exception
    {
        this.drawer.withStrokeColor(Color.RED)
                   .drawPolygon(polygon -> polygon.add(Point.of(0, 0))
                                                  .add(Point.of(100, 100)))
                   .drawCircle(Point.of(0, 0), 10);
    }

    @Test
    public void testCursor() throws Exception
    {
        this.drawer.withStrokeColor(Color.RED)
                   .cursor(cursor -> cursor.moveTo(Point.of(0, 0))
                                           .startDrawing()
                                           .move(Distance.of(10, 10))
                                           .stopDrawing()
                                           .drawCircle(5));
    }

    private DrawProcessor createDrawProcessor()
    {
        return new DrawProcessor() {
            @Override
            public void polyline(List<Point> points, ColorProvider color, double strokeWidth)
            {

            }

            @Override
            public void circle(Point center, double radius, double strokeWidth, ColorProvider strokeColor, ColorProvider fillColor)
            {

            }
        };
    }

}
