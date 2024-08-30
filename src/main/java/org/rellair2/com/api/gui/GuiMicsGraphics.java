package org.rellair2.com.api.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class GuiMicsGraphics extends IGuiGraphics {
    ResourceLocation location;
    int width, height;
    int strength;

    public GuiMicsGraphics(ResourceLocation location, GuiGraphics graphics, int x, int y, int width, int height, int strength) {
        super(graphics, x, y);
        this.width = width;
        this.height = height;
        this.location = location;
        this.strength = strength;
    }

    @Override
    public void render() {
        graphics.setColor(1, 1, 1, strength * 0.01f);
        graphics.blit(location, x, y, -90, 0, 0, width, height, width, height);
        graphics.setColor(1, 1, 1, 1);
    }
}
