package org.rellair2.com.api.biome.type;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public interface IBiomeType {

    public List<ResourceKey<Biome>> getBiomes();
}
