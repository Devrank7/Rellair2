package org.rellair2.com.api.manager.valuable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.api.controllers.RemoteController;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.api.valuable.IValuable;
import org.rellair2.com.network.ModMessage;

import java.util.List;
import java.util.function.Function;

public abstract class IValuableManager<T,V extends IHandler<T>> implements IObserverTick {

    protected Player player;

    protected int frequency = 5;

    public IValuableManager(Player player) {
        this.player = player;
    }

    protected final void send(ServerPlayer serverPlayer, T value) {
        if (getPacket() == null) return;
        ModMessage.sendToPlayerValuable(getPacket().apply(value), serverPlayer);
    }

    @Override
    public void tick() {
        if (player.level().getGameTime() % frequency == 0) {
            frequencyTick();
            RemoteController<T,V> remoteController = new RemoteController<>(
                    new RemoteController.RemoteControllerData<>(
                            getHandlers(),
                            player,
                            getValuable()));
            remoteController.start();
            this.send((ServerPlayer) player, getValuable().getValue());
        }
    }

    public abstract void readValue(CompoundTag compoundTag);

    public abstract void writeValue(CompoundTag compoundTag);

    protected abstract void frequencyTick();

    protected abstract List<V> getHandlers();

    protected abstract Function<T, IValuablePacket<T>> getPacket();

    public abstract IValuable<T> getValuable();

    protected abstract String saveName();
}
