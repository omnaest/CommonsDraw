package org.omnaest.utils.draw.internal;

import org.omnaest.utils.draw.domain.Distance;
import org.omnaest.utils.draw.domain.Point;

public class VectorUtils
{

    public static Point add(Point point, Distance distance)
    {
        double x = point.getX() + distance.getX();
        double y = point.getY() + distance.getY();
        return Point.of(x, y);
    }

}
