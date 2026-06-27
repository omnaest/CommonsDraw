package org.omnaest.utils.draw.domain;

public enum CanvasSize implements CanvasSizeProvider
{
    _100x100(100, 100), _1000x1000(1000, 1000), _10000x10000(10000, 10000);

    private double width;
    private double height;

    private CanvasSize(double width, double height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth()
    {
        return width;
    }

    @Override
    public double getHeight()
    {
        return height;
    }

}
