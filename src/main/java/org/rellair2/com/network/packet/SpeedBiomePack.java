package org.rellair2.com.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

public class SpeedBiomePack extends IValuablePacket<Float> {
    public SpeedBiomePack(Float temp) {
        super(temp);
    }

    public static SpeedBiomePack decode(FriendlyByteBuf buffer) {
        return new SpeedBiomePack(buffer.readFloat());
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(temp);
    }

    @Override
    public void handleClient(CustomPayloadEvent.Context event) {
        ((IRPlayer) Minecraft.getInstance().player).
                getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).
                temperatureDirection().setSpeed(temp);
    }
}
