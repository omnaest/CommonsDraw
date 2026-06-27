package org.omnaest.utils.draw.internal;

import org.omnaest.utils.draw.domain.Distance;
import org.omnaest.utils.draw.domain.Point;

public class Vector implements Point, Distance
{
    private double x;
    private double y;

    public Vector(double x, double y)
    {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX()
    {
        return this.x;
    }

    @Override
    public double getY()
    {
        return this.y;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Vector [x=")
               .append(this.x)
               .append(", y=")
               .append(this.y)
               .append("]");
        return builder.toString();
    }

}
