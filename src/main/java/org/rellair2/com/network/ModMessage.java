package org.rellair2.com.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import org.rellair2.com.Rellair2;
import org.rellair2.com.api.networking.IClientPacket;
import org.rellair2.com.api.networking.IServerPacket;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.network.packet.*;

public class ModMessage {

    public static final SimpleChannel INSTANCE = ChannelBuilder.named(ResourceLocation.tryBuild(Rellair2.MODID, "main"))
            .clientAcceptedVersions(((status, version) -> true))
            .serverAcceptedVersions(((status, version) -> true))
            .networkProtocolVersion(1)
            .simpleChannel();


    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        INSTANCE.messageBuilder(ChangeTemperature.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ChangeTemperature::encode)
                .decoder(ChangeTemperature::decode)
                .consumerMainThread(ChangeTemperature::handle)
                .add();
        INSTANCE.messageBuilder(WorldTemperaturePack.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(WorldTemperaturePack::encode)
                .decoder(WorldTemperaturePack::decode)
                .consumerMainThread(WorldTemperaturePack::handle)
                .add();
        INSTANCE.messageBuilder(BiomeTemperaturePack.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(BiomeTemperaturePack::encode)
                .decoder(BiomeTemperaturePack::decode)
                .consumerMainThread(BiomeTemperaturePack::handle)
                .add();
        INSTANCE.messageBuilder(BiomeHumidPack.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(BiomeHumidPack::encode)
                .decoder(BiomeHumidPack::decode)
                .consumerMainThread(BiomeHumidPack::handle)
                .add();
        INSTANCE.messageBuilder(SpeedBiomePack.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SpeedBiomePack::encode)
                .decoder(SpeedBiomePack::decode)
                .consumerMainThread(SpeedBiomePack::handle)
                .add();
        INSTANCE.messageBuilder(ActivityPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ActivityPacket::encode)
                .decoder(ActivityPacket::decode)
                .consumerMainThread(ActivityPacket::handle)
                .add();
        INSTANCE.messageBuilder(WetLevelPack.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(WetLevelPack::encode)
                .decoder(WetLevelPack::decode)
                .consumerMainThread(WetLevelPack::handle)
                .add();
    }

    public static void sendToServer(IServerPacket msg) {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }

    public static void sendToPlayerValuable(IValuablePacket<?> msg, ServerPlayer player) {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }

    public static void sendToPlayer(IClientPacket msg, ServerPlayer player) {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }
}
