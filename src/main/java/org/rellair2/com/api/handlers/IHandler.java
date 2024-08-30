package org.rellair2.com.api.handlers;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.valuable.IValuable;

public interface IHandler<T> {
    public void handle(Player player, IValuable<T> value);
}
