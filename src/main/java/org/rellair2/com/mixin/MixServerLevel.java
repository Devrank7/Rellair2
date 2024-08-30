package org.rellair2.com.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.rellair2.com.api.mixins.IRLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public abstract class MixServerLevel extends Level implements IRLevel {

    public MixServerLevel(WritableLevelData p_270739_, ResourceKey<Level> p_270683_, RegistryAccess p_270200_, Holder<DimensionType> p_270240_, Supplier<ProfilerFiller> p_270692_, boolean p_270904_, boolean p_270470_, long p_270248_, int p_270466_) {
        super(p_270739_, p_270683_, p_270200_, p_270240_, p_270692_, p_270904_, p_270470_, p_270248_, p_270466_);
    }

    @Unique
    private long rellair2$ticksSinceLastRain;

    @Inject(method = "advanceWeatherCycle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/WritableLevelData;isThundering()Z",ordinal = 1,shift = At.Shift.BEFORE))
    private void beforeAdvanceWeatherCycle(CallbackInfo ci) {
        if (!this.levelData.isRaining()) {
            rellair2$ticksSinceLastRain++;
        } else {
            rellair2$ticksSinceLastRain = 0;
        }
        if (!this.levelData.isThundering()) {
            rellair2$ticksSinceLastRain++;
        } else {
            rellair2$ticksSinceLastRain = 0;
        }
    }

    @Override
    public long rellair2$ticksSinceLastRain() {
        return rellair2$ticksSinceLastRain;
    }
}
