package org.rellair2.com.api.handlers;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

public interface IChangeHandler<T> extends IHandler<T> {

    default Holder<Biome> getBiomeHolder(@NotNull Player player) {
        return player.level().getBiome(player.blockPosition());
    }
    default Biome getBiome(@NotNull Player player) {
        return getBiomeHolder(player).value();
    }

    @Override
    default boolean needToChange() {
        return true;
    }
}
