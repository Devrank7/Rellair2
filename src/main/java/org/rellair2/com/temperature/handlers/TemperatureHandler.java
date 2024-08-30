package org.rellair2.com.temperature.handlers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.biome.IBiomeClimateSettingsBase;
import org.rellair2.com.api.biome.type.BiomeType;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;

public abstract class TemperatureHandler extends ITemperatureHandler<Float> {

    protected IBiomeClimateManager climateManager;

    public TemperatureHandler(ITemperatureValuable<Float, ITemperatureHandler<Float>> temperatureValuable, IBiomeClimateManager climateManager) {
        super(temperatureValuable);
        this.climateManager = climateManager;
    }

    protected IBiomeClimateSettingsBase climateSettingsForBiomeInPosPlayer(@NotNull Player player) {
        ResourceKey<Biome> biomeResourceKey = getBiomeHolder(player).unwrapKey().orElseThrow();
        return climateManager.getBiomeClimateSettings().get(BiomeType.getBiome(biomeResourceKey));
    }
}
