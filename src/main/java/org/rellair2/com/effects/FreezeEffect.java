package org.rellair2.com.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FreezeEffect extends MobEffect {

    public int tickFreeze;
    public static final int MAX_TICK_FROZEN = 142;

    public FreezeEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int p_19468_) {
        //entity.isInPowderSnow = true;
        float duration = entity.getEffect(RMobEffectRegister.EFFECT_FREEZE.getHolder().orElseThrow()).getDuration();

        GameRules gameRules = entity.level().getGameRules();
        BlockPos playerPos = entity.blockPosition();
        //entity.sendSystemMessage(Component.literal("tick frozen = " + entity.getTicksFrozen()));
        entity.setTicksFrozen(tickFreeze);

// Получаем тип блока под ногами игрока
        BlockState blockState = entity.level().getBlockState(playerPos);
        if (blockState.is(Blocks.POWDER_SNOW)) {
            gameRules.getRule(GameRules.RULE_FREEZE_DAMAGE).set(true,entity.getServer());
            if (duration <= 5) {
                entity.setTicksFrozen(0);
            }
        } else {
            if (duration <= 5) {
                gameRules.getRule(GameRules.RULE_FREEZE_DAMAGE).set(true,entity.getServer());
                entity.setTicksFrozen(0);
            } else {
                gameRules.getRule(GameRules.RULE_FREEZE_DAMAGE).set(entity.level().getDifficulty() == Difficulty.HARD,entity.getServer());
            }
        }
        if (duration > 70) {
            tickFreeze = Math.min(tickFreeze + 1,MAX_TICK_FROZEN);
        } else {
            tickFreeze = Math.max(tickFreeze - 1,0);
        }
        return super.applyEffectTick(entity, p_19468_);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_19455_, int p_19456_) {
        return true;
    }
}
