package org.omnaest.utils.draw.domain;

public interface RGBAColorProvider
{
    public int getRed();

    public int getGreen();

    public int getBlue();

    public double getOpacity();

    public static RGBAColorProvider of(int red, int green, int blue, double opacity)
    {
        return new DefaultRGBAColorProvider(red, green, blue, opacity);
    }
}
