package org.rellair2.com.network;

import net.minecraft.network.FriendlyByteBuf;

public interface CodeObject {
    public void encode(FriendlyByteBuf buffer);

    public CodeObject decode(FriendlyByteBuf buffer);
}
