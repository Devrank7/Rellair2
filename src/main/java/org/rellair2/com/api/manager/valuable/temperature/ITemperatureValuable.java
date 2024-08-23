package org.rellair2.com.api.manager.valuable.temperature;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.manager.valuable.IValuableManager;

public abstract class ITemperatureValuable<T,V extends IHandler<T>> extends IValuableManager<T,V> {
    public ITemperatureValuable(Player player) {
        super(player);
    }
}
