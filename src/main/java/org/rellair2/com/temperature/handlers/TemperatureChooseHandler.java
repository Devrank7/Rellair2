package org.rellair2.com.temperature.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

public class TemperatureChooseHandler extends TemperatureChangeHandler {

    public TemperatureChooseHandler(ITemperatureValuable<Float, ITemperatureHandler<Float>> temperatureValuable, IBiomeClimateManager climateManager) {
        super(temperatureValuable, climateManager);
    }

    @Override
    protected float currentTemperatureForBiome(@NotNull Player player) {
        return getTemperature(player, player.level().getDayTime());
    }

    private float getTemperature(Player player, float timeOfDay) {
        var climateSettingsBase = climateSettingsForBiomeInPosPlayer(player);
        float wet = temperatureValuable.wetManager().getWetness();
        float x = 24F + (wet * getWaterMultiplier(player, false) / 2500F);
        float y = player.isInWater() ? climateSettingsBase.getWaterTemperature(player, timeOfDay) :
                climateSettingsBase.getTemperatureDaytime(player, timeOfDay);
        float z = (player.isUnderWater() || player.isInWater() ? 2f : 1f) * (wet * 0.85f) * (0.000045f);
        return (y - ((x - y) * z));
    }

    @Override
    protected float speedModifier(@NotNull Player player) {
        var climateSettingsBase = climateSettingsForBiomeInPosPlayer(player);
        float l1 = checkAir(player, 10) ? climateSettingsBase.getHumidityDaytime(player, player.level().getDayTime()) : (climateSettingsBase.getBaseHumidity() + 100);
        float l2 = getWetMultiplier(player);
        float l3 = l1 * l2;
        return 0.00005f * l3;
    }

    private boolean checkAir(Player player, int radius) {
        BlockPos playerPos = player.blockPosition().above();
        for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-radius, 0, -radius), playerPos.offset(radius, 0, radius))) {
            if (player.level().canSeeSky(pos)) {
                return true;
            }
        }
        System.err.println("Can't see sky");
        return false;
    }

    private float getWetMultiplier(Player player) {
        // System.err.println("wet level = " + temperatureValuable.wetManager().getWetness());
        return 1 + (temperatureValuable.wetManager().getWetness() / 1500) * getWaterMultiplier(player, true);
    }

    private float getWaterMultiplier(Player player, boolean pow) {
        return player.isUnderWater() ? ((pow ? 3 * 3 : 3)) : (player.isInWater() ? (pow ? 2.5f * 2.5f : 2.5f) : 1f);
    }
}
