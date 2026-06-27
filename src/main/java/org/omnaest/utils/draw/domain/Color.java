package org.omnaest.utils.draw.domain;

public enum Color implements ColorProvider
{
    RED(255, 0, 0, 1.0), BLUE(0, 0, 255, 1.0), GREEN(0, 255, 0, 1.0), BLACK(0, 0, 0, 1.0), WHITE(255, 255, 255, 1.0);

    private RGBAColorProvider rgba;

    private Color(int red, int green, int blue, double opacity)
    {
        this.rgba = RGBAColorProvider.of(red, green, blue, opacity);
    }

    @Override
    public RGBAColorProvider asRGBA()
    {
        return this.rgba;
    }
}
