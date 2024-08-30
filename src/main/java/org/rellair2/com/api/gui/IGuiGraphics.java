package org.rellair2.com.api.gui;

import net.minecraft.client.gui.GuiGraphics;

public abstract class IGuiGraphics {
    protected GuiGraphics graphics;
    protected int x, y;

    public IGuiGraphics(GuiGraphics graphics, int x, int y) {
        this.graphics = graphics;
        this.x = x;
        this.y = y;
    }

    public abstract void render();
}
