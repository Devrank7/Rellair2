package org.rellair2.com.biome;

import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.biome.random.RandomOffset;
import org.rellair2.com.api.biome.type.BiomeType;

public class BiomeClimateManagerImpl extends IBiomeClimateManager {

    @Override
    protected void init() {
        put(BiomeType.SNOWY, SimpleBiomeClimateSettings::new, -5.0f, 50f);
        put(BiomeType.ICE_SPIKES, SimpleBiomeClimateSettings::new, -15.0f, 50f);
        put(BiomeType.SNOWY_TAIGA, SimpleBiomeClimateSettings::new, -12.0f, 40f);
        put(BiomeType.FROZEN_OCEAN, SimpleBiomeClimateSettings::new, 5, 95f);
        put(BiomeType.SNOWY_MOUNTAINS, SimpleBiomeClimateSettings::new, -10, 70);
        put(BiomeType.MOUNTAINS_GROVE, SimpleBiomeClimateSettings::new, -7, 60);
        put(BiomeType.FROZEN_PEAKS, SimpleBiomeClimateSettings::new, -25f, 75f);
        put(BiomeType.TAIGA, SimpleBiomeClimateSettings::new, 11.5f, 60f);
        put(BiomeType.COLD_OCEAN, SimpleBiomeClimateSettings::new, 18f, 90f);
        put(BiomeType.MEADOW, SimpleBiomeClimateSettings::new, 19.5f, 60f);
        put(BiomeType.WINDSWEPT_HILLS, SimpleBiomeClimateSettings::new, 9f, 30f);
        put(BiomeType.PLAINS, SimpleBiomeClimateSettings::new, 25f, 50f);
        put(BiomeType.FOREST, SimpleBiomeClimateSettings::new, 23.5f, 70f);
        put(BiomeType.CHERRY_GROVE, SimpleBiomeClimateSettings::new, 20.0f, 70f);
        put(BiomeType.BIRCH_FOREST, SimpleBiomeClimateSettings::new, 21.5f, 60f);
        put(BiomeType.SWAMP, SimpleBiomeClimateSettings::new, 25.0f, 80f);
        put(BiomeType.MANGROVE_SWAMP, SimpleBiomeClimateSettings::new, 27.0f, 85f);
        put(BiomeType.JUNGLE, SimpleBiomeClimateSettings::new, 29.0f, 80.0f);
        put(BiomeType.SPARSE_JUNGLE, SimpleBiomeClimateSettings::new, 29.0f, 70.0f);
        put(BiomeType.MUSHROOM_FIELDS, SimpleBiomeClimateSettings::new, 26.0f, 90.0f);
        put(BiomeType.OCEAN, SimpleBiomeClimateSettings::new, 24.0f, 90.0f);
        put(BiomeType.BEACH, SimpleBiomeClimateSettings::new, 24.0f, 55.0f);
        put(BiomeType.DRY_MOUNTAINS, SimpleBiomeClimateSettings::new, 25.0f, 30.0f);
        putRiver(BiomeType.RIVER, 90.0f, 25.0f);
        put(BiomeType.SAVANNA, SimpleBiomeClimateSettings::new, 32.0f, 40.0f);
        put(BiomeType.DESERT_AND_BADLANDS, SimpleBiomeClimateSettings::new, 36.0f, 15.0f);
        putComplex(BiomeType.WOODLANDS_BADLANDS, ComplexBiomeClimateSettings::new, 33.0f, 30.0f, RandomOffset::new);
        putStatic(BiomeType.LUSH_CAVES, 22.5f, 50.0f);
        putStatic(BiomeType.CAVE, 18f, 90.0f);
        putStatic(BiomeType.NETHER, 70f, 1.0f);
        putStatic(BiomeType.END, 20f, 50f);
    }
}
