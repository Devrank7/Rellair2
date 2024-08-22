package org.rellair2.com.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;
import org.rellair2.com.api.mixins.IRPlayer;

public class ChangeTemperature extends IValuablePacket<Float> {

    public ChangeTemperature(float temp) {
        super(temp);
    }
    public static ChangeTemperature decode(FriendlyByteBuf buffer) {
        return new ChangeTemperature(buffer.readFloat());
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(temp);
    }

    public void handle(CustomPayloadEvent.Context event) {
        if (event.isClientSide()) {
            LocalPlayer localPlayer = Minecraft.getInstance().player;
            if (localPlayer instanceof IRPlayer irPlayer) {
                System.err.println("Set temperature: " + temp);
                irPlayer.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().setValue(temp);
            }
        }
    }

}
