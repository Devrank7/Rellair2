package org.rellair2.com.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import org.rellair2.com.Rellair2;
import org.rellair2.com.network.packet.ChangeTemperature;
import org.rellair2.com.api.networking.IValuablePacket;

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
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }

    public static void sendToPlayer(IValuablePacket<?> msg, ServerPlayer player) {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }
}
