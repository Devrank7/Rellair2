package org.rellair2.com.api.networking;

import net.minecraftforge.event.network.CustomPayloadEvent;

public abstract class IServerPacket implements IPacket{

    @Override
    public void handle(CustomPayloadEvent.Context event) {
        if (event.isServerSide()) {
            handleServer(event);
        }
    }
    public abstract void handleServer(CustomPayloadEvent.Context event);
}
