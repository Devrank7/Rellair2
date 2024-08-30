package org.rellair2.com.biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.biome.IBiomeClimateSettingsBase;
import org.rellair2.com.api.biome.type.BiomeType;

import java.util.Objects;
import java.util.Optional;

public class RiverBiomeClimateSettings implements IBiomeClimateSettingsBase {
    private final float temperature;
    private final float humidity;
    private final IBiomeClimateManager biomeClimateManager;

    public RiverBiomeClimateSettings(float humidity,float temperature, IBiomeClimateManager biomeClimateManager) {
        this.humidity = humidity;
        this.biomeClimateManager = biomeClimateManager;
        this.temperature = temperature;
    }

    @Override
    public float getTemperatureDaytime(Player player, float timeOfDay) {
        return Optional.of(Objects.requireNonNull(tryFindOther(player)).getTemperatureDaytime(player, timeOfDay)).orElse(temperature);
    }
    private IBiomeClimateSettingsBase tryFindOther(Player player) {
        ResourceKey<Biome> biome = findNearestNonRiverBiome(player.level(), player.blockPosition(), 50);
        System.err.println("Nearest biome = " + biome);
        if (biome != Biomes.RIVER) {
            return biomeClimateManager.getBiomeClimateSettings().get(BiomeType.getBiome(biome));
        }
        return null;
    }
    private ResourceKey<Biome> findNearestNonRiverBiome(Level level, BlockPos startPos, int radius) {
        BlockPos nearestBiomePos = null;
        double nearestDistance = Double.MAX_VALUE;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos checkPos = startPos.offset(x, 0, z); // Проверяем смещения по X и Z
                ResourceKey<Biome> biome = level.getBiome(checkPos).unwrapKey().orElseThrow();
                if (!isRiverBiome(biome)) {
                    double distance = startPos.distSqr(new Vec3i(checkPos.getX(), checkPos.getY(), checkPos.getZ()));
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestBiomePos = checkPos;
                    }
                }
            }
        }
        return level.getBiome(Optional.ofNullable(nearestBiomePos).orElse(startPos)).unwrapKey().orElseThrow(); // Возвращаем позицию ближайшего биома, который не является River
    }

    private boolean isRiverBiome(ResourceKey<Biome> biome) {
        return biome == Biomes.RIVER;
    }

    @Override
    public float getWaterTemperature(Player player, float timeOfDay) {
        return Optional.ofNullable(tryFindOther(player)).orElse(this).getWaterTemperature(player, timeOfDay);
    }

    @Override
    public float getBaseTemperature() {
        return temperature;
    }

    @Override
    public float getBaseHumidity() {
        return humidity;
    }
}
