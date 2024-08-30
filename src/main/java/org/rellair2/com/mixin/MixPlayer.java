package org.rellair2.com.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.rellair2.com.api.manager.data.IDataManager;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.activity.ActivityManager;
import org.rellair2.com.activity.IActivityManager;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Player.class)
public abstract class MixPlayer extends LivingEntity implements IRPlayer {
    @Unique
    private final Map<Class<? extends IDataManager<?>>, IDataManager<?>> save_data = Map.of(
            TemperatureDataManager.class, new TemperatureDataManager(myself())
    );
    private IActivityManager activityManager = new ActivityManager(0f);

    public MixPlayer(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Unique
    public Player myself() {
        return (Player) (Object) this;
    }

    @Inject(method = "readAdditionalSaveData", at = @At(value = "TAIL"))
    public void onReadAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        save_data.values().forEach(data -> data.readAdditionalSaveData(nbt));
    }

    @Inject(method = "addAdditionalSaveData", at = @At(value = "TAIL"))
    public void onAddAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        save_data.values().forEach(data -> data.addAdditionalSaveData(nbt));
    }


    @Override
    public Map<Class<? extends IDataManager<?>>, IDataManager<?>> getAllData() {
        return this.save_data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IDataManager<?>> T getData(Class<T> clazz) {
        return (T) save_data.get(clazz);
    }

    @Override
    public void copyData(IRPlayer player) {
        activityManager.copyFrom(player.getActivityManager());
        for (Map.Entry<Class<? extends IDataManager<?>>, IDataManager<?>> entry : player.getAllData().entrySet()) {
            save_data.get(entry.getKey()).copyFrom(entry.getValue());
        }
    }

    @Override
    public void setActivityManager(IActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    @Override
    public IActivityManager getActivityManager() {
        return this.activityManager;
    }

    @Inject(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V", shift = At.Shift.AFTER))
    public void actualHurtOn(DamageSource p_36312_, float p_36313_, CallbackInfo ci) {
        if (p_36312_.getFoodExhaustion() >= 0.1f) {
            activityManager.updateActivityLevel((Player) (Object) this,IActivityManager.ActivityType.DAMAGE_ACTUAL,p_36312_.getFoodExhaustion() * 10f);
        }
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V", shift = At.Shift.AFTER))
    public void actualHurtOn(CallbackInfo ci) {
        activityManager.updateActivityLevel((Player) (Object) this, IActivityManager.ActivityType.ATTACKING,1f);
    }

    @Inject(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V", shift = At.Shift.AFTER, ordinal = 0))
    public void jump1(CallbackInfo ci) {
        activityManager.updateActivityLevel((Player) (Object) this, IActivityManager.ActivityType.JUMPING_AND_SPRINTING,1f);
    }

    @Inject(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V", shift = At.Shift.AFTER, ordinal = 1))
    public void jump2(CallbackInfo ci) {
        activityManager.updateActivityLevel((Player) (Object) this, IActivityManager.ActivityType.JUMPING,1f);
    }
}
