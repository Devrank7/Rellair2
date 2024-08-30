package org.rellair2.com.gui;

import org.rellair2.com.api.gui.*;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.api.temperature.IArrowDrawer;
import org.rellair2.com.api.temperature.IClientTemperatureType;
import org.rellair2.com.api.temperature.ITemperatureType;
import org.rellair2.com.api.temperature.TempType;
import org.rellair2.com.api.temperature.fsm.StateMachine;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

import java.util.List;
import java.util.Optional;

public class GuiRenderTemperature extends IGuiRender {

    public StateMachine<ITemperatureType> stateMachine = new StateMachine<>();

    @Override
    public void tick(GuiInfo guiInfo) {
        stateMachine.tick();
        super.tick(guiInfo);
    }

    @Override
    protected void addOptions(List<IGuiGraphics> options, GuiInfo guiInfo) {
        float temperature = ((IRPlayer) guiInfo.player()).getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().getValue();
        IClientTemperatureType clientTemperatureType = stateMachine.setIfOther((ITemperatureType) TempType.getNorClientType(temperature).apply(guiInfo.width(), guiInfo.height()), guiInfo.width(), guiInfo.height(), temperature);
        IArrowDrawer arrow = stateMachine.setIfOther((ITemperatureType) TempType.getNorClientType(temperature).apply(guiInfo.width(), guiInfo.height()), guiInfo.width(), guiInfo.height(), temperature);
        Optional.ofNullable(arrow).ifPresent(p -> {
            options.add(new GuiArrowGraphics(guiInfo.graphics(), p.getDirection().getV(), p.getDirection().getU(Math.min(15, p.getDirectionIndex())), guiInfo.width() / 2 - 24, (guiInfo.height() - 54)));
        });
        Optional.ofNullable(clientTemperatureType).ifPresent(i -> {
            options.add(new GuiSpriteGraphics(i.getMain(), guiInfo.graphics()));
            i.getMics().keySet().forEach(p -> {
                AdvanceGuiData advanceGuiData = i.getMics().get(p);
                options.add(new GuiMicsGraphics(advanceGuiData.getLocation(), guiInfo.graphics(), 0, 0, guiInfo.width(), guiInfo.height(), advanceGuiData.getStrength()));
            });
        });
        stateMachine.lastDraws().forEach(p -> {
            options.add(new GuiMicsGraphics(p.getLocation(), guiInfo.graphics(),0,0, guiInfo.width(), guiInfo.height(), p.getStrength()));
        });
    }

}
