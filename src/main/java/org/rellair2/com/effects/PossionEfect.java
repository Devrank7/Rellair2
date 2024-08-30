package org.rellair2.com.effects;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PossionEfect extends MobEffect {

    public PossionEfect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);

    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int p_19468_) {
        int duration = entity.getEffect(RMobEffectRegister.EFFECT_THIRS_POSSION.getHolder().orElseThrow()).getDuration();
        if (duration < 8) {
            // SyncTemperature.setPosionEffect(false);
        } else {
            // SyncTemperature.setPosionEffect(true);
        }
        return super.applyEffectTick(entity, p_19468_);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_19455_, int p_19456_) {
        return true;
    }
}
