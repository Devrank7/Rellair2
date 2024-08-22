package org.rellair2.com.gui;

import org.rellair2.com.api.gui.IGuiRender;
import org.rellair2.com.api.gui.GuiInfo;

public class GuiInvoker {

    public static void invoke(IGuiRender guiRender, GuiInfo guiInfo) {
        guiRender.tick(guiInfo);
    }
}
