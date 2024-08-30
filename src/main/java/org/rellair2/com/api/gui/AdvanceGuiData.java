package org.rellair2.com.api.gui;

import net.minecraft.resources.ResourceLocation;

public class AdvanceGuiData extends GuiData {
    private ResourceLocation location;
    private int strength;

    public AdvanceGuiData(int x, int y, int width, int height, ResourceLocation location, int strength) {
        super(x, y, width, height);
        this.location = location;
        this.strength = strength;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public AdvanceGuiData setLocation(ResourceLocation location) {
        this.location = location;
        return this;
    }

    public int getStrength() {
        return strength;
    }

    public AdvanceGuiData setStrength(int strength) {
        this.strength = strength;
        return this;
    }
}
