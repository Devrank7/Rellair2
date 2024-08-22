package org.rellair2.com.api.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.rellair2.com.Rellair2;
import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.api.mixins.IRPlayer;

@Mod.EventBusSubscriber(modid = Rellair2.MODID)
public class TickEvent {
    @SubscribeEvent
    public static void tick(net.minecraftforge.event.TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            notifyObserverAll(event.player);
        }
    }
    public static void notifyObserverAll(Player player) {
        ((IRPlayer) player).getAllData().values().forEach(d -> d.valuableManagers().values().forEach(IObserverTick::tick)); // Only server side
    }
}
