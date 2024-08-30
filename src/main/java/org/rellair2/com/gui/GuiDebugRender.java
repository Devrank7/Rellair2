package org.rellair2.com.gui;

import org.rellair2.com.Config;
import org.rellair2.com.api.gui.GuiInfo;
import org.rellair2.com.api.gui.GuiTextGraphics;
import org.rellair2.com.api.gui.IGuiGraphics;
import org.rellair2.com.api.gui.IGuiRender;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.temperature.TemperatureHelper;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;
import org.rellair2.com.temperature.type.TypeTemperature;

import java.util.List;

public class GuiDebugRender extends IGuiRender {

    @Override
    protected void addOptions(List<IGuiGraphics> options, GuiInfo guiInfo) {
        options.add(new GuiTextGraphics("Temperature player: " + getTemperature(guiInfo), guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - 74), 14737632));
        options.add(new GuiTextGraphics("Temperature world: " + getTemperatureOfWorld(guiInfo), guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - 54), 14737632));
        options.add(new GuiTextGraphics("Temperature nor player: " + TemperatureHelper.getPlayerTemperature(getTemperatureOfWorld(guiInfo)), guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - 94), 14737632));
        options.add(new GuiTextGraphics("Temperature humid player: " + getHumidOfWorld(guiInfo), guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - 104), 14737632));
        options.add(new GuiTextGraphics("Activity player: " + getActivity(guiInfo), guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - 114), 14737632));
        options.add(new GuiTextGraphics("Biome temperature: " + getTemperatureOfBiome(guiInfo), guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - 124), 14737632));
        int i = 134;
        options.add(new GuiTextGraphics("Temperature: %f %s°".
                formatted(TemperatureHelper.getTemperatureIn(getTemperatureOfWorld(guiInfo), Config.Temperature.type), Config.Temperature.type.name()),
                guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - i), 14737632));
        options.add(new GuiTextGraphics("Wet level: %3f of".formatted(getWetLevel(guiInfo)),guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - i - 10), 14737632));
        options.add(new GuiTextGraphics("Speed level: %5f of".formatted(getDirection(guiInfo).getSpeed() * 100),guiInfo.graphics(), ((guiInfo.width() / 2) + 40), (guiInfo.height() - i - 20), 14737632));
    }

    public float getTemperature(GuiInfo guiInfo) {
        return castTo(guiInfo).getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().getValue();
    }
    public float getWetLevel(GuiInfo guiInfo) {
        return castTo(guiInfo).getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).wetManager().getWetness() / 40;
    }

    public float getActivity(GuiInfo guiInfo) {
        return castTo(guiInfo).getActivityManager().getActivityLevel();
    }

    public float getTemperatureOfWorld(GuiInfo guiInfo) {
        return getDirection(guiInfo).getTemperatureDirection();
    }

    public float getTemperatureOfBiome(GuiInfo guiInfo) {
        return getDirection(guiInfo).getTemperatureOfBiome();
    }

    public ITemperatureValuable.TemperatureDirection getDirection(GuiInfo guiInfo) {
        return castTo(guiInfo).getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class)
                .temperatureDirection();
    }

    public float getHumidOfWorld(GuiInfo guiInfo) {
        return getDirection(guiInfo).getHumidityOfBiome();
    }

    private IRPlayer castTo(GuiInfo guiInfo) {
        return (IRPlayer) guiInfo.player();
    }
}
