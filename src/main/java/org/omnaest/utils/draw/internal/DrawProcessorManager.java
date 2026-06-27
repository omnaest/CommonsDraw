package org.omnaest.utils.draw.internal;

import java.util.ArrayList;
import java.util.List;

import org.omnaest.utils.draw.domain.ColorProvider;
import org.omnaest.utils.draw.domain.DrawProcessor;
import org.omnaest.utils.draw.domain.Point;

public class DrawProcessorManager implements DrawProcessor
{
    private List<DrawProcessor> drawProcessors = new ArrayList<>();

    public DrawProcessorManager addProcessor(DrawProcessor drawProcessor)
    {
        if (drawProcessor != null)
        {
            this.drawProcessors.add(drawProcessor);
        }
        return this;
    }

    @Override
    public void polyline(List<Point> points, ColorProvider color, double strokeWidth)
    {
        this.drawProcessors.forEach(processor -> processor.polyline(points, color, strokeWidth));
    }

    @Override
    public void circle(Point center, double radius, double strokeWidth, ColorProvider strokeColor, ColorProvider fillColor)
    {
        this.drawProcessors.forEach(processor -> processor.circle(center, radius, strokeWidth, strokeColor, fillColor));
    }

}
