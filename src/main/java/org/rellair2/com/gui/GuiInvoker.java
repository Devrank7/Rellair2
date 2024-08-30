package org.rellair2.com.gui;

import org.rellair2.com.api.gui.GuiInfo;
import org.rellair2.com.api.gui.IGuiRender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GuiInvoker {
    private final List<IGuiRender> guiRenders = new ArrayList<>();

    public GuiInvoker invoke(Supplier<IGuiRender> guiRender) {
        guiRenders.add(guiRender.get());
        return this;
    }


    public void tickOn(GuiInfo guiInfo) {
        guiRenders.forEach(guiRender -> guiRender.tick(guiInfo));
    }
}
