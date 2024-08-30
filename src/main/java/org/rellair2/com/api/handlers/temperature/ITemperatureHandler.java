package org.rellair2.com.api.handlers.temperature;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;

public abstract class ITemperatureHandler<T> implements IHandler<T> {

    protected ITemperatureValuable<T,ITemperatureHandler<T>> temperatureValuable;

    public ITemperatureHandler(ITemperatureValuable<T, ITemperatureHandler<T>> temperatureValuable) {
        this.temperatureValuable = temperatureValuable;
    }

    protected Holder<Biome> getBiomeHolder(@NotNull Player player) {
        return player.level().getBiome(player.blockPosition());
    }
    protected Biome getBiome(@NotNull Player player) {
        return getBiomeHolder(player).value();
    }
}
