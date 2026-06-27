package org.omnaest.utils.draw.internal;

import java.util.function.UnaryOperator;

import org.omnaest.utils.draw.domain.CanvasSize;
import org.omnaest.utils.draw.domain.CanvasSizeProvider;
import org.omnaest.utils.draw.domain.Point;

public class PointTranslator implements UnaryOperator<Point>
{
    private CanvasSizeProvider canvasSizeProvider = CanvasSize._100x100;

    public PointTranslator setCanvasSizeProvider(CanvasSizeProvider canvasSizeProvider)
    {
        this.canvasSizeProvider = canvasSizeProvider;
        return this;
    }

    @Override
    public Point apply(Point point)
    {
        double x = point.getX() / this.canvasSizeProvider.getWidth();
        double y = point.getY() / this.canvasSizeProvider.getHeight();
        return Point.of(x, y);
    }

    public double apply(double length)
    {
        return length / Math.max(this.canvasSizeProvider.getWidth(), this.canvasSizeProvider.getHeight());
    }

}
