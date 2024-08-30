package org.rellair2.com.temperature.handlers.deal;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.network.CodeObject;

public interface IStateHandler<T> {

    public void handle(Player player, T value);
}
