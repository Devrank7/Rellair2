package org.rellair2.com.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.checkerframework.checker.units.qual.A;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

public class ActivityPacket extends IValuablePacket<Float> {

    public ActivityPacket(Float temp) {
        super(temp);
    }

    public static ActivityPacket decode(FriendlyByteBuf buffer) {
        return new ActivityPacket(buffer.readFloat());
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(temp);
    }

    @Override
    public void handleClient(CustomPayloadEvent.Context event) {
        assert Minecraft.getInstance().player instanceof IRPlayer;
        ((IRPlayer) Minecraft.getInstance().player).getActivityManager().setActivityLevel(temp);
    }
}