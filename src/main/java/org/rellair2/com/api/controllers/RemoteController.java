package org.rellair2.com.api.controllers;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.valuable.IValuable;

import java.util.List;

public class RemoteController<T,V extends IHandler<T>> {

    private RemoteControllerData<T,V> handler;

    public RemoteController(RemoteControllerData<T,V> handler) {
        this.handler = handler;
    }

    private void press(V handler) {
        T newValue = handler.handle(this.handler.player, this.handler.valuable.getValue());
        if (handler.needToChange()) {
            this.handler.valuable.setValue(newValue);
        }
    }
    public void start() {
        for (V handler : this.handler.handlers) {
            this.press(handler);
        }
    }

    public record RemoteControllerData<T,V>(List<V> handlers, Player player, IValuable<T> valuable) {}
}
