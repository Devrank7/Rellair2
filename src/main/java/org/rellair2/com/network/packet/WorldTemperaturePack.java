package org.rellair2.com.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

public class WorldTemperaturePack extends IValuablePacket<Float> {

    public WorldTemperaturePack(Float temp) {
        super(temp);
    }

    public static WorldTemperaturePack decode(FriendlyByteBuf buffer) {
        return new WorldTemperaturePack(buffer.readFloat());
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(temp);
    }

    @Override
    public void handleClient(CustomPayloadEvent.Context event) {
            ((IRPlayer)Minecraft.getInstance().player).
                    getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).
                    temperatureDirection().setTemperatureDirection(temp);
    }
}
