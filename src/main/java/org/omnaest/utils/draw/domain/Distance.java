package org.omnaest.utils.draw.domain;

import org.omnaest.utils.draw.internal.Vector;

public interface Distance
{
    public double getY();

    public double getX();

    public static Distance of(double x, double y)
    {
        return new Vector(x, y);
    }

    public static Distance between(Point from, Point to)
    {
        return new Vector(to.getX() - from.getX(), to.getY() - from.getY());
    }
}
