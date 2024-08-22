package org.rellair2.com.api.handlers;

import net.minecraft.world.entity.player.Player;

public interface IHandler<T> {
    public T handle(Player player, T value);
    public boolean needToChange();
}
