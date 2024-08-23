package org.rellair2.com.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.rellair2.com.api.manager.data.IDataManager;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.api.mixins.IRPlayer;
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
        if (save_data.containsKey(TemperatureDataManager.class)) {
            System.err.println("I am here");
        }
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
        for (Map.Entry<Class<? extends IDataManager<?>>, IDataManager<?>> entry : player.getAllData().entrySet()) {
            save_data.get(entry.getKey()).copyFrom(entry.getValue());
        }
    }
}
