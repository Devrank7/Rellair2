package org.rellair2.com.api.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public abstract class IClientPacket implements IPacket {

    @Override
    public void handle(CustomPayloadEvent.Context event) {
        if (event.isClientSide()) {
            handleClient(event);
        }
    }
    public abstract void handleClient(CustomPayloadEvent.Context event);
}
