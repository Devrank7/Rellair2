package org.rellair2.com.temperature.handlers;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;
import org.rellair2.com.api.valuable.IValuable;

public abstract class TemperatureChangeHandler extends TemperatureHandler {

    public TemperatureChangeHandler(ITemperatureValuable<Float, ITemperatureHandler<Float>> temperatureValuable, IBiomeClimateManager climateManager) {
        super(temperatureValuable, climateManager);
    }

    @Override
    public void handle(Player player, IValuable<Float> value) {
        float currentTemperatureForBiome = currentTemperatureForBiome(player);
        float speed = speedModifier(player);
        // System.err.println("currentTemperatureForBiome = " + currentTemperatureForBiome);
        this.temperatureValuable.temperatureDirection().setTemperatureDirection(currentTemperatureForBiome);
        this.temperatureValuable.temperatureDirection().setTemperatureOfBiome(climateSettingsForBiomeInPosPlayer(player).getTemperatureDaytime(player, player.level().getDayTime()));
        this.temperatureValuable.temperatureDirection().setHumidityOfBiome(climateSettingsForBiomeInPosPlayer(player).getHumidityDaytime(player, player.level().getDayTime()));
        this.temperatureValuable.temperatureDirection().setSpeed(speed);
    }

    protected abstract float currentTemperatureForBiome(@NotNull Player player);

    protected abstract float speedModifier(@NotNull Player player);
}
