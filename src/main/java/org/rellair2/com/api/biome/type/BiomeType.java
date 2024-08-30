package org.rellair2.com.api.biome.type;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BiomeType implements IBiomeType {

    FOREST(List.of(
            Biomes.FOREST,
            Biomes.FLOWER_FOREST,
            Biomes.DARK_FOREST
    )),
    BIRCH_FOREST(List.of(
            Biomes.BIRCH_FOREST,
            Biomes.OLD_GROWTH_BIRCH_FOREST
    )),
    CHERRY_GROVE(List.of(
            Biomes.CHERRY_GROVE
    )),
    PLAINS(List.of(
            Biomes.PLAINS,
            Biomes.SUNFLOWER_PLAINS
    )),
    SWAMP(List.of(
            Biomes.SWAMP
    )),
    MANGROVE_SWAMP(List.of(
            Biomes.MANGROVE_SWAMP
    )),
    JUNGLE(List.of(
            Biomes.JUNGLE,
            Biomes.BAMBOO_JUNGLE
    )),
    SPARSE_JUNGLE(List.of(
            Biomes.SPARSE_JUNGLE
    )),
    MUSHROOM_FIELDS(List.of(
            Biomes.MUSHROOM_FIELDS
    )),
    OCEAN(List.of(
            Biomes.OCEAN,
            Biomes.DEEP_OCEAN,
            Biomes.LUKEWARM_OCEAN,
            Biomes.DEEP_LUKEWARM_OCEAN
    )),
    RIVER(List.of(
            Biomes.RIVER
    )),
    BEACH(List.of(
            Biomes.BEACH
    )),
    TAIGA(List.of(
            Biomes.TAIGA,
            Biomes.OLD_GROWTH_PINE_TAIGA,
            Biomes.OLD_GROWTH_SPRUCE_TAIGA
    )),
    COLD_OCEAN(List.of(
            Biomes.COLD_OCEAN,
            Biomes.DEEP_COLD_OCEAN
    )),
    MEADOW(List.of(
            Biomes.MEADOW
    )),
    WINDSWEPT_HILLS(List.of(
            Biomes.WINDSWEPT_HILLS,
            Biomes.WINDSWEPT_GRAVELLY_HILLS,
            Biomes.WINDSWEPT_FOREST,
            Biomes.STONY_SHORE
    )),
    SNOWY(List.of(
            Biomes.SNOWY_PLAINS,
            Biomes.SNOWY_BEACH
    )),
    FROZEN_OCEAN(List.of(
            Biomes.FROZEN_OCEAN,
            Biomes.DEEP_FROZEN_OCEAN,
            Biomes.FROZEN_RIVER
    )),
    ICE_SPIKES(List.of(
            Biomes.ICE_SPIKES
    )),
    SNOWY_TAIGA(List.of(
        Biomes.SNOWY_TAIGA
    )),
    MOUNTAINS_GROVE(List.of(
            Biomes.GROVE
    )),
    SNOWY_MOUNTAINS(List.of(
            Biomes.SNOWY_SLOPES
    )),
    FROZEN_PEAKS(List.of(
            Biomes.FROZEN_PEAKS,
            Biomes.JAGGED_PEAKS
    )),
    DRY_MOUNTAINS(List.of(
            Biomes.STONY_PEAKS
    )),
    WARM_OCEAN(List.of(
            Biomes.WARM_OCEAN
    )),
    DESERT_AND_BADLANDS(List.of(
            Biomes.DESERT,
            Biomes.BADLANDS,
            Biomes.ERODED_BADLANDS
    )),
    WOODLANDS_BADLANDS(List.of(
            Biomes.WOODED_BADLANDS
            )),
    SAVANNA(List.of(
            Biomes.SAVANNA,
            Biomes.WINDSWEPT_SAVANNA,
            Biomes.SAVANNA_PLATEAU
            )),
    LUSH_CAVES(List.of(
            Biomes.LUSH_CAVES
    )),
    CAVE(List.of(
            Biomes.DRIPSTONE_CAVES,
            Biomes.DEEP_DARK,
            Biomes.THE_VOID
    )),
    NETHER(List.of(
            Biomes.NETHER_WASTES,
            Biomes.SOUL_SAND_VALLEY,
            Biomes.BASALT_DELTAS,
            Biomes.CRIMSON_FOREST,
            Biomes.WARPED_FOREST
    )),
    END(List.of(
            Biomes.THE_END,
            Biomes.END_BARRENS,
            Biomes.SMALL_END_ISLANDS,
            Biomes.END_HIGHLANDS,
            Biomes.END_MIDLANDS
    ));


    private final List<ResourceKey<Biome>> biomes;

    BiomeType(List<ResourceKey<Biome>> biomes) {
        this.biomes = biomes;
    }

    @Override
    public List<ResourceKey<Biome>> getBiomes() {
        return biomes;
    }
    public static IBiomeType getBiome(ResourceKey<Biome> biomeKey) {
        return Arrays.stream(values()).filter(biomeType -> biomeType.getBiomes().contains(biomeKey)).findFirst().orElseThrow(() -> new IllegalArgumentException("No biome found with key " + biomeKey));
    }
}
