package org.rellair2.com.api.gui;

public interface IGuiData extends IGuiSimple{
    public int getWidth();
    public int getHeight();
    public IGuiData setWidth(int width);
    public IGuiData setHeight(int height);
}
