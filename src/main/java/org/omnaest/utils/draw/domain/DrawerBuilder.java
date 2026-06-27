package org.omnaest.utils.draw.domain;

public interface DrawerBuilder
{
    public DrawerBuilder withCanvasSize(CanvasSize canvasSize);

    public DrawerBuilder withCanvasSize(CanvasSizeProvider canvasSize);

    public DrawerBuilder addDrawProcessor(DrawProcessor drawProcessor);

    public Drawer build();
}
