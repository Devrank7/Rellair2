package org.rellair2.com.effects;

import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.List;

public class BlurEffect extends MobEffect {

    public static float timeTick;

    public static float strengthEffect = 0f;

    private static Uniform blur;
    private static Field POST_PASSES = null;

    public float timeDamage;

    private boolean isNotAdd;

    static {
        try {
            POST_PASSES = ObfuscationReflectionHelper.findField(PostChain.class, "f_110009_");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BlurEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int p_19468_) {
        try {
            int timeDuration = entity.getEffect(RMobEffectRegister.EFFECT_HOT_BLUR.getHolder().orElseThrow()).getDuration();
            if (timeDuration < 20) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal(Minecraft.getInstance().gameRenderer.currentEffect().getName()));
                Minecraft.getInstance().gameRenderer.shutdownEffect();
                System.err.println("change off = " + timeDuration);
                strengthEffect = 0;

            } else if (timeDuration < 80) {
                strengthEffect = Math.max(0, strengthEffect - 0.04f);
            }
            if (timeTick > 1) {
                try {
                    Minecraft.getInstance().gameRenderer.loadEffect(ResourceLocation.parse("shaders/post/blur.json"));
                } catch (IllegalStateException ei) {
                    System.err.println("change off ei");
                }
                Minecraft.getInstance().gameRenderer.loadEffect(ResourceLocation.parse("shaders/post/blur.json"));
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("by 1 = "));
                blur = ((List<PostPass>) POST_PASSES.get(Minecraft.getInstance().gameRenderer.currentEffect())).getFirst().getEffect().getUniform("Radius");
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("effect by 2 = " + strengthEffect));
                if (blur != null) {
                    blur.set(strengthEffect);
                }
                if (strengthEffect < 6) {
                    if (!isNotAdd) {
                        strengthEffect += 0.1f;
                    }
                }
                timeTick = 0;

            }
            if (timeDamage >= 30) {
                if (entity.level().getDifficulty() == Difficulty.HARD) {
                    entity.hurt(new DamageSource(Holder.direct(new DamageType("very hot temperature", 0.1F, DamageEffects.BURNING))), 1);
                }
                timeDamage = 0;
            }
            timeTick += 0.1f;
            timeDamage += 0.05f;
        } catch (Exception ignored) {
        }
        return super.applyEffectTick(entity, p_19468_);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_19455_, int p_19456_) {
        return true;
    }
}
