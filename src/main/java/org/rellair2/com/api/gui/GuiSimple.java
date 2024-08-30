package org.rellair2.com.api.gui;

public class GuiSimple implements IGuiSimple {
    int x, y;

    public GuiSimple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public IGuiSimple setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public IGuiSimple setY(int y) {
        this.y = y;
        return this;
    }
}
