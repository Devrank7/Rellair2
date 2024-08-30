package org.rellair2.com.api.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class GuiTextGraphics extends IGuiGraphics {
    private final String text;
    private final int color;

    public GuiTextGraphics(String text, GuiGraphics graphics, int x, int y, int color) {
        super(graphics, x, y);
        this.text = text;
        this.color = color;

    }

    @Override
    public void render() {
        Component component = Component.literal(text);
        Font font = Minecraft.getInstance().font;
        graphics.drawStringWithBackdrop(font,component, x, y, font.width(component), color);
    }
}
