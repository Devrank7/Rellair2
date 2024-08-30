package org.rellair2.com.temperature.handlers;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.block.IThermalBlock;
import org.rellair2.com.api.block.strtegy.IThermalStrategy;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;
import org.rellair2.com.api.valuable.IValuable;

import java.util.List;

public class TemperatureBlockHandler extends ITemperatureHandler<Float> {

    private final IThermalStrategy strategy;
    private final List<IThermalBlock> thermalBlocks;

    public TemperatureBlockHandler(ITemperatureValuable<Float, ITemperatureHandler<Float>> temperatureValuable, IThermalStrategy strategy, List<IThermalBlock> thermalBlocks) {
        super(temperatureValuable);
        this.strategy = strategy;
        this.thermalBlocks = thermalBlocks;
    }

    @Override
    public void handle(Player player, IValuable<Float> value) {
        float v = strategy.calculateExtraTemperature(player, thermalBlocks);
        //System.err.println("v = " + v);
        v = Mth.clamp(v, -200, 200);
        float add = (v * 0.4f);
        float max = temperatureValuable.temperatureDirection().getTemperatureDirection() + add;
        // System.err.println("Temperature of world = " + max);
        this.temperatureValuable.temperatureDirection().setTemperatureDirection(max);
    }
}
