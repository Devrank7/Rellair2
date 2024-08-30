package org.rellair2.com.api.networking;

import net.minecraft.network.FriendlyByteBuf;
import org.rellair2.com.network.CodeObject;

import java.util.List;

public abstract class IClientObjectPacket<T extends CodeObject> extends IClientPacket {
    protected List<T> codeObjects;

    public IClientObjectPacket(List<T> codeObjects) {
        this.codeObjects = codeObjects;
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(codeObjects.size()); // Сначала записываем размер списка
        for (CodeObject codeObject : codeObjects) {
            codeObject.encode(buffer); // Кодируем каждый объект в списке
        }
    }
}
