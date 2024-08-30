package org.rellair2.com.api.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffects;

public class GuiSpriteGraphics extends IGuiGraphics {
    private final int width, height;
    private final ResourceLocation location;
    private final int strength;

    public GuiSpriteGraphics(ResourceLocation location, GuiGraphics graphics, int x, int y, int width, int height, int strength) {
        super(graphics, x, y);
        this.width = width;
        this.height = height;
        this.location = location;
        this.strength = strength;
    }

    public GuiSpriteGraphics(AdvanceGuiData advanceGuiData, GuiGraphics graphics) {
        super(graphics, advanceGuiData.getX(), advanceGuiData.getY());
        this.width = advanceGuiData.getWidth();
        this.height = advanceGuiData.getHeight();
        this.location = advanceGuiData.getLocation();
        this.strength = advanceGuiData.getStrength();
    }

    @Override
    public void render() {
        graphics.setColor(1F, 1F, 1F, strength * 0.01f);
        graphics.blitSprite(location, x, y, width, height);
        graphics.setColor(1F, 1F, 1F, 1F);
    }
}
