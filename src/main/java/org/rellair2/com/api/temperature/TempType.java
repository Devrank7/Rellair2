package org.rellair2.com.api.temperature;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.RegistryObject;
import org.rellair2.com.Rellair2;
import org.rellair2.com.effects.RMobEffectRegister;
import org.rellair2.com.temperature.type.KilledTemperature;
import org.rellair2.com.temperature.type.NormalTemperatyreType;
import org.rellair2.com.temperature.type.UnkilledTemperature;

import java.util.List;
import java.util.function.BiFunction;

public class TempType {
    private static final ResourceLocation ICE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "textures/gui/sprites/hud/overlay/c.png");
    private static final ResourceLocation HELL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "textures/gui/sprites/hud/overlay/hell_hot.png");
    private static final ResourceLocation HELL_HYPER_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "textures/gui/sprites/hud/overlay/hyperthermia_outline.png");
    private static final ResourceLocation ICY_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i");
    private static final ResourceLocation BITER_COLD_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i1");
    private static final ResourceLocation COLD_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i2");
    private static final ResourceLocation CHILLY_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i3");
    private static final ResourceLocation NORMAL_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i4");
    private static final ResourceLocation WARM_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i5");
    private static final ResourceLocation HOT_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i6");
    private static final ResourceLocation VERY_HOT_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i7");
    private static final ResourceLocation HELL_SET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/set/i8");
    private static final ITemperatureType ICY = new KilledTemperature(Float.MIN_VALUE, List.of(
            getMobEffectInstance(MobEffects.WEAKNESS, 3),
            getMobEffectInstance(MobEffects.DIG_SLOWDOWN, 3), getMobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3), getMobEffectInstance(RMobEffectRegister.EFFECT_FREEZE, 1)),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i"), ICE_TEXTURE, ICY_SET_TEXTURE,List.of());


    private static final ITemperatureType BITTER_COLD = new UnkilledTemperature(3f, List.of(
            getMobEffectInstance(MobEffects.WEAKNESS, 1),
            getMobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1)),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i1"), BITER_COLD_SET_TEXTURE, ICE_TEXTURE, 100);


    private static final ITemperatureType COLD = new UnkilledTemperature(9f, List.of(
            getMobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 0)),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i2"), COLD_SET_TEXTURE, ICE_TEXTURE, 75);

    private static final ITemperatureType CHILLY = new NormalTemperatyreType(15f,
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i3"), CHILLY_SET_TEXTURE);

    private static final ITemperatureType NORMAL = new NormalTemperatyreType(20f,
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i4"), NORMAL_SET_TEXTURE);

    private static final ITemperatureType WARM = new NormalTemperatyreType(25.5f,
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i5"), WARM_SET_TEXTURE);
    private static final ITemperatureType HOT = new UnkilledTemperature(30f, List.of(
            getMobEffectInstance(MobEffects.WEAKNESS, 0), getMobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 0), getMobEffectInstance(RMobEffectRegister.EFFECT_THIRS_POSSION, 1)),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i6"), HOT_SET_TEXTURE, HELL_TEXTURE, 75);

    private static final ITemperatureType VERY_HOT = new UnkilledTemperature(35f, List.of(
            getMobEffectInstance(MobEffects.WEAKNESS, 2), getMobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1), getMobEffectInstance(RMobEffectRegister.EFFECT_THIRS_POSSION, 2)),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i7"), VERY_HOT_SET_TEXTURE, HELL_TEXTURE, 100);

    private static final ITemperatureType HELL = new KilledTemperature(42f, List.of(
            getMobEffectInstance(RMobEffectRegister.EFFECT_HOT_BLUR, 1),
            getMobEffectInstance(MobEffects.WEAKNESS, 3),
            getMobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3), getMobEffectInstance(MobEffects.CONFUSION, 3), getMobEffectInstance(RMobEffectRegister.EFFECT_THIRS_POSSION, 3)),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/main/i8"), HELL_TEXTURE, HELL_SET_TEXTURE,List.of(HELL_HYPER_TEXTURE));
    public static List<ITemperatureType> list = List.of(ICY, BITTER_COLD, COLD, CHILLY, NORMAL, WARM, HOT, VERY_HOT, HELL);


    public static ITemperatureType findBy(float temperature) {
        return list.stream().filter(p -> temperature >= p.start_temperature && temperature < next_start(p)).findFirst().orElse(ICY);
    }

    private static float next_start(ITemperatureType current) {
        int index = list.indexOf(current);
        if (index == -1 || index + 1 >= list.size()) {
            return Float.MAX_VALUE;
        }
        return list.get(index + 1).start_temperature;
    }

    public static BiFunction<Integer, Integer, IClientTemperatureType> getNorClientType(float temperature) {
        return findBy(temperature)::normalize;
    }

    public static IServerTemperatureType getServerType(float temperature) {
        return findBy(temperature);
    }


    public static MobEffectInstance getMobEffectInstance(RegistryObject<MobEffect> effect, int level) {
        return getMobEffectInstance(effect.getHolder().orElseThrow(), level);
    }

    public static MobEffectInstance getMobEffectInstance(Holder<MobEffect> effect, int level) {
        return new MobEffectInstance(effect, 120, level, false, false, false);
    }

    public enum TemperatureType {
        ICY(TempType.ICY),
        BITTER_COLD(TempType.BITTER_COLD),
        COLD(TempType.COLD),
        CHILLY(TempType.CHILLY),
        NORMAL(TempType.NORMAL),
        WARM(TempType.WARM),
        HOT(TempType.HOT),
        VERY_HOT(TempType.VERY_HOT),
        HELL(TempType.HELL);
        final ITemperatureType type;

        TemperatureType(ITemperatureType type) {
            this.type = type;
        }

        public ITemperatureType getType() {
            return type;
        }
    }
}
