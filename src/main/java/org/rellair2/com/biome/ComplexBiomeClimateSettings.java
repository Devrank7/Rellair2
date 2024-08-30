package org.rellair2.com.biome;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.rellair2.com.api.biome.ClimateSettings;
import org.rellair2.com.api.biome.random.IRandomOffset;

import java.util.function.Function;

public class ComplexBiomeClimateSettings extends SimpleBiomeClimateSettings {

    public ComplexBiomeClimateSettings(ClimateSettings climateSettings, @Nullable Function<Long, IRandomOffset> functionOffset) {
        super(climateSettings, functionOffset);
    }

    @Override
    public float getTemperatureDaytime(Player player, float timeOfDay) {
        float daytime = timeOfDay % 24000;
        float abs_falloff = Math.abs(climateSettings.humidity() - 100.0f) * 0.01f;
        float nor = (float) Math.sin((daytime / 24000.0f) * Math.TAU);
        float s = (nor * climateSettings.temperatureOffset()) * (abs_falloff + 0.1f);
        return climateSettings.temperature() + s + complexFunctionOffset((ServerLevel) player.level(), timeOfDay);
    }
}
