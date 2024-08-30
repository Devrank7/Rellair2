package org.rellair2.com.effects;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GraceEffect extends MobEffect {

    public GraceEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity p_19467_, int p_19468_) {
        int duration = p_19467_.getEffect(RMobEffectRegister.EFFECT_GRACE_CLIMATE.getHolder().orElseThrow()).getDuration();
        // SyncTemperature.setIsGraceEffect(duration > 5);
        return super.applyEffectTick(p_19467_, p_19468_);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_19455_, int p_19456_) {
        return true;
    }
}
