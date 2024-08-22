package org.rellair2.com.api.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public abstract class IValuablePacket<T> {

    protected T temp;

    public IValuablePacket(T temp) {
        this.temp = temp;
    }
    public abstract void encode(FriendlyByteBuf buffer);
    public abstract void handle(CustomPayloadEvent.Context event);
}
