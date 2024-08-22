package org.rellair2.com.api.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public record GuiInfo(Gui gui, GuiGraphics graphics, Player player, int width, int height) {}
