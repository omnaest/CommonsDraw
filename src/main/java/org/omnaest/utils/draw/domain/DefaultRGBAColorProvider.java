package org.omnaest.utils.draw.domain;

public class DefaultRGBAColorProvider implements RGBAColorProvider
{
    private int    red;
    private int    green;
    private int    blue;
    private double opacity;

    public DefaultRGBAColorProvider(int red, int green, int blue, double opacity)
    {
        super();
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    @Override
    public int getRed()
    {
        return red;
    }

    @Override
    public int getGreen()
    {
        return green;
    }

    @Override
    public int getBlue()
    {
        return blue;
    }

    @Override
    public double getOpacity()
    {
        return opacity;
    }

}
