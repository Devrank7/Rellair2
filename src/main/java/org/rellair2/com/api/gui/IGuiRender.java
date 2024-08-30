package org.rellair2.com.api.gui;

import net.minecraft.resources.ResourceLocation;
import org.rellair2.com.api.mixins.IRPlayer;

import java.util.ArrayList;
import java.util.List;

public abstract class IGuiRender {

    protected abstract void addOptions(List<IGuiGraphics> options, GuiInfo guiInfo);

    public void tick(GuiInfo guiInfo) {
        List<IGuiGraphics> options = new ArrayList<>();
        addOptions(options, guiInfo);
        options.forEach(IGuiGraphics::render);
    }
}
