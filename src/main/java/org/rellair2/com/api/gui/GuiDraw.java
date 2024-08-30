package org.rellair2.com.api.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.rellair2.com.network.CodeObject;

public record GuiDraw(ResourceLocation location, float strength) implements CodeObject {
    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(location); // Кодируем первый ResourceLocation// Кодируем второй ResourceLocation
        buffer.writeFloat(strength); // Кодируем float поле
    }

    @Override
    public GuiDraw decode(FriendlyByteBuf buffer) {
        ResourceLocation location = buffer.readResourceLocation(); // Декодируем первый ResourceLocation
        float strength = buffer.readFloat(); // Декодируем float поле
        return new GuiDraw(location, strength); // Создаем и возвращаем новый объект GuiDraw
    }

}
