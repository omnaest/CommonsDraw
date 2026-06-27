package org.omnaest.utils.draw.domain;

import java.util.List;

public interface DrawProcessor
{
    public void polyline(List<Point> points, ColorProvider color, double strokeWidth);

    public void circle(Point center, double radius, double strokeWidth, ColorProvider strokeColor, ColorProvider fillColor);
}
