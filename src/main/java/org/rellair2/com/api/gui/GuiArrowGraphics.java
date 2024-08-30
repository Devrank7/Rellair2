package org.rellair2.com.api.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.rellair2.com.Rellair2;

public class GuiArrowGraphics extends IGuiGraphics {

    int v, u;
    private final ResourceLocation OVERLAY = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "textures/gui/sprites/hud/direction/icons.png");
    private final ResourceLocation OVERLAY1 = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/direction/direct");

    public GuiArrowGraphics(GuiGraphics graphics, int v, int u, int x, int y) {
        super(graphics, x, y);
        this.v = v;
        this.u = u;
    }

    @Override
    public void render() {
        graphics.blit(OVERLAY, x, y,u,v, 16, 16);
    }
}
