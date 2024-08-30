package org.rellair2.com.api.event;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.rellair2.com.Rellair2;
import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.network.ModMessage;
import org.rellair2.com.network.packet.WetLevelPack;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = Rellair2.MODID)
public class TickEvent {
    @SubscribeEvent
    public static void tick(net.minecraftforge.event.TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            notifyObserverAll(event.player);
            if (event.player.level().getGameTime() % 5 == 0) {
                ModMessage.sendToPlayer(new WetLevelPack(irPlayer(event.player).getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).wetManager().getWetness()), (ServerPlayer) event.player);
            }
        }
        if (event.player.getRandom().nextInt(40) == 0) {
            Optional.ofNullable(Minecraft.getInstance().gameRenderer.currentEffect()).ifPresentOrElse(p -> {
                System.err.println("name = " + p.getName());
            }, () -> System.err.println("No effect"));
        }
    }

    public static void notifyObserverAll(Player player) {
        irPlayer(player).getAllData().values().forEach(IObserverTick::tick);
        irPlayer(player).getActivityManager().tick(player);
        irPlayer(player).getAllData().values().forEach(d -> d.valuableManagers().values().forEach(IObserverTick::tick)); // Only server side
    }

    public static IRPlayer irPlayer(Player player) {
        return (IRPlayer) player;
    }
}
