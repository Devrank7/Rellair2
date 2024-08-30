package org.rellair2.com.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.rellair2.com.Rellair2;

public class RMobEffectRegister {

    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            Rellair2.MODID);
    public static final RegistryObject<MobEffect> EFFECT_FREEZE = EFFECT.register("effect_freeze",
            () -> new FreezeEffect(MobEffectCategory.HARMFUL,8346754));
    public static final RegistryObject<MobEffect> EFFECT_HOT_BLUR = EFFECT.register("effect_hot_blur",
            () -> new BlurEffect(MobEffectCategory.HARMFUL,4357754));
    public static final RegistryObject<MobEffect> EFFECT_THIRS_POSSION = EFFECT.register("possion",
            () -> new PossionEfect(MobEffectCategory.HARMFUL,5197754));
    public static final RegistryObject<MobEffect> EFFECT_GRACE_CLIMATE = EFFECT.register("grace",
            () -> new PossionEfect(MobEffectCategory.BENEFICIAL,8991854));
}
