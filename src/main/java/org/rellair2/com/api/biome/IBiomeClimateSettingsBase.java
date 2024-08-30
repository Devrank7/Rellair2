package org.rellair2.com.api.biome;

import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.player.Player;

public interface IBiomeClimateSettingsBase {


    default float getTemperatureDaytime(Player player, float timeOfDay) {
        return getBaseTemperature();
    }

    default float getHumidityDaytime(Player player, float timeOfDay) {
        return getBaseHumidity() + 100F;
    }

    float getWaterTemperature(Player player, float timeOfDay);

    float getBaseTemperature();

    float getBaseHumidity();
}
