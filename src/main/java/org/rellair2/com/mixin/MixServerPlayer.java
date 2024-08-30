package org.rellair2.com.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.rellair2.com.activity.IActivityManager;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;
import org.rellair2.com.api.mixins.IRPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class MixServerPlayer extends Player {

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

    @Shadow
    private static boolean didNotMove(double p_310773_, double p_310271_, double p_312126_) {
        return false;
    }

    @Inject(method = "restoreFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;onUpdateAbilities()V", ordinal = 0, shift = At.Shift.AFTER))
    private void injectKeepInventoryOrSpectatorMessage(ServerPlayer p_9016_, boolean p_9017_, CallbackInfo ci) {
        IRPlayer irPlayer = (IRPlayer) p_9016_;
        IRPlayer irPlayer2 = (IRPlayer) this;
        if (Minecraft.getInstance().gameRenderer.currentEffect() != null)
            Minecraft.getInstance().gameRenderer.shutdownEffect();
        irPlayer2.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).wetManager().copyFrom(irPlayer.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).wetManager());
        if (!p_9017_ && (this.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) || p_9016_.isSpectator())) {
            System.err.println("irPlayer.getData(TemperatureDataManager.class).getValuable().getValue() = " + irPlayer.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().getValue());
            System.err.println("Respawned with keep inventory or spectator");
            irPlayer2.copyData(irPlayer);
        }
    }

    /**
     * @author Devlink
     * @reason Add activity cause. I won't make this code more readable because this is Minecraft code
     */
    @Overwrite
    public void checkMovementStatistics(double p_310268_, double p_310728_, double p_313145_) {
        IRPlayer irPlayer = (IRPlayer) this;
        Player player = (Player) this;
        if (!this.isPassenger() && !didNotMove(p_310268_, p_310728_, p_313145_)) {
            if (this.isSwimming()) {
                int i = Math.round((float) Math.sqrt(p_310268_ * p_310268_ + p_310728_ * p_310728_ + p_313145_ * p_313145_) * 100.0F);
                if (i > 0) {
                    this.awardStat(Stats.SWIM_ONE_CM, i);
                    this.causeFoodExhaustion(0.01F * (float) i * 0.01F);
                }
                irPlayer.getActivityManager().updateActivityLevel(player, IActivityManager.ActivityType.SWIMMING, 1 + Math.max(0, i - 24));
            } else if (this.isEyeInFluid(FluidTags.WATER)) {
                int j = Math.round((float) Math.sqrt(p_310268_ * p_310268_ + p_310728_ * p_310728_ + p_313145_ * p_313145_) * 100.0F);
                if (j > 0) {
                    this.awardStat(Stats.WALK_UNDER_WATER_ONE_CM, j);
                    this.causeFoodExhaustion(0.01F * (float) j * 0.01F);
                }
                irPlayer.getActivityManager().updateActivityLevel(player, IActivityManager.ActivityType.IN_WATER, 1f);
            } else if (this.isInWater()) {
                int k = Math.round((float) Math.sqrt(p_310268_ * p_310268_ + p_313145_ * p_313145_) * 100.0F);
                if (k > 0) {
                    this.awardStat(Stats.WALK_ON_WATER_ONE_CM, k);
                    this.causeFoodExhaustion(0.01F * (float) k * 0.01F);
                }
            } else if (this.onClimbable()) {
                if (p_310728_ > 0.0) {
                    this.awardStat(Stats.CLIMB_ONE_CM, (int) Math.round(p_310728_ * 100.0));
                }
                irPlayer.getActivityManager().updateActivityLevel(player, IActivityManager.ActivityType.CLIMBING, 1);
            } else if (this.onGround()) {
                int l = Math.round((float) Math.sqrt(p_310268_ * p_310268_ + p_313145_ * p_313145_) * 100.0F);
                if (l > 0) {
                    if (this.isSprinting()) {
                        this.awardStat(Stats.SPRINT_ONE_CM, l);
                        this.causeFoodExhaustion(0.1F * (float) l * 0.01F);
                        System.err.println("Move speed = " + l);
                        irPlayer.getActivityManager().updateActivityLevel(player, IActivityManager.ActivityType.SPRINTING, 1 + Math.max(0, l - 24));
                    } else if (this.isCrouching()) {
                        this.awardStat(Stats.CROUCH_ONE_CM, l);
                        this.causeFoodExhaustion(0.0F * (float) l * 0.01F);
                        irPlayer.getActivityManager().updateActivityLevel(player, IActivityManager.ActivityType.CROUCHING, 1);
                    } else {
                        this.awardStat(Stats.WALK_ONE_CM, l);
                        this.causeFoodExhaustion(0.0F * (float) l * 0.01F);
                    }
                }
            } else if (this.isFallFlying()) {
                int i1 = Math.round((float) Math.sqrt(p_310268_ * p_310268_ + p_310728_ * p_310728_ + p_313145_ * p_313145_) * 100.0F);
                this.awardStat(Stats.AVIATE_ONE_CM, i1);
            } else {
                int j1 = Math.round((float) Math.sqrt(p_310268_ * p_310268_ + p_313145_ * p_313145_) * 100.0F);
                if (j1 > 25) {
                    this.awardStat(Stats.FLY_ONE_CM, j1);
                }
            }
        }
    }
}
