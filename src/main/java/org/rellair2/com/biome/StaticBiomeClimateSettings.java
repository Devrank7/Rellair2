package org.rellair2.com.biome;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.biome.IBiomeClimateSettingsBase;

public record StaticBiomeClimateSettings(float temperature, float humidity) implements IBiomeClimateSettingsBase {
    @Override
    public float getBaseTemperature() {
        return temperature;
    }

    @Override
    public float getBaseHumidity() {
        return humidity;
    }

    @Override
    public float getWaterTemperature(Player player, float timeOfDay) {
        return temperature;
    }
}
