package org.omnaest.utils.draw.domain;

import org.omnaest.utils.draw.internal.Vector;

public interface Point
{
    public double getY();

    public double getX();

    public static Point of(double x, double y)
    {
        return new Vector(x, y);
    }

    public static Point zero()
    {
        return of(0, 0);
    }

}
