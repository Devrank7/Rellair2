package org.rellair2.com.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.rellair2.com.api.networking.IValuablePacket;

public class PChange extends IValuablePacket<String> {

    public PChange(String s) {
        super(s);
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(temp);
    }

    public static PChange decode(FriendlyByteBuf buffer) {
        return new PChange(buffer.readUtf());
    }

    @Override
    public void handle(CustomPayloadEvent.Context event) {

    }
}
