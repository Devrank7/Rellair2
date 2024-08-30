package org.rellair2.com.api.gui;

import net.minecraft.resources.ResourceLocation;

public class GuiData implements IGuiData {
    private int x, y;
    private int width, height;

    public GuiData(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    public IGuiData setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public IGuiData setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public IGuiData setWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public IGuiData setHeight(int height) {
        this.height = height;
        return this;
    }
}
