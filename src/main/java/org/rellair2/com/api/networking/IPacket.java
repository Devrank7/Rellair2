package org.rellair2.com.api.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public interface IPacket {
    public abstract void encode(FriendlyByteBuf buffer);
    public abstract void handle(CustomPayloadEvent.Context event);
}
