package org.rellair2.com.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;
import org.rellair2.com.api.mixins.IRPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class MixServerPlayer extends Player {

    public MixServerPlayer(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Override
    @Shadow
    public boolean isSpectator() {
        return false;
    }

    @Override
    @Shadow
    public boolean isCreative() {
        return false;
    }

    @Inject(method = "restoreFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;replaceWith(Lnet/minecraft/world/entity/player/Inventory;)V", ordinal = 1, shift = At.Shift.AFTER))
    private void injectKeepInventoryOrSpectatorMessage(ServerPlayer p_9016_, boolean p_9017_, CallbackInfo ci) {
        IRPlayer irPlayer = (IRPlayer) p_9016_;
        IRPlayer irPlayer2 = (IRPlayer) this;
        System.err.println("irPlayer.getData(TemperatureDataManager.class).getValuable().getValue() = " + irPlayer.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().getValue());
        System.err.println("Respawned with keep inventory or spectator");
        irPlayer2.copyData(irPlayer);

    }
}
