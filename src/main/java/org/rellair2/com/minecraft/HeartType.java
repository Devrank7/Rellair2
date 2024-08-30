package org.rellair2.com.minecraft;

import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.rellair2.com.Rellair2;
import org.rellair2.com.effects.RMobEffectRegister;

@OnlyIn(Dist.CLIENT)
public enum HeartType {
    CONTAINER(
            ResourceLocation.withDefaultNamespace("hud/heart/container"),
            ResourceLocation.withDefaultNamespace("hud/heart/container_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/container"),
            ResourceLocation.withDefaultNamespace("hud/heart/container_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/container_hardcore"),
            ResourceLocation.withDefaultNamespace("hud/heart/container_hardcore_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/container_hardcore"),
            ResourceLocation.withDefaultNamespace("hud/heart/container_hardcore_blinking")
    ),
    NORMAL(
            ResourceLocation.withDefaultNamespace("hud/heart/full"),
            ResourceLocation.withDefaultNamespace("hud/heart/full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/half"),
            ResourceLocation.withDefaultNamespace("hud/heart/half_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/hardcore_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/hardcore_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/hardcore_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/hardcore_half_blinking")
    ),
    POISIONED(
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_half_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_hardcore_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_hardcore_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_hardcore_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/poisoned_hardcore_half_blinking")
    ),
    WITHERED(
            ResourceLocation.withDefaultNamespace("hud/heart/withered_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_half_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_hardcore_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_hardcore_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_hardcore_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/withered_hardcore_half_blinking")
    ),
    ABSORBING(
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_half_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_hardcore_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_hardcore_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_hardcore_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/absorbing_hardcore_half_blinking")
    ),
    FROZEN(
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_half_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_hardcore_full"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_hardcore_full_blinking"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_hardcore_half"),
            ResourceLocation.withDefaultNamespace("hud/heart/frozen_hardcore_half_blinking")
    ),
    OVERHEATED(
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_full"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_full_blinking"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_half"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_half_blinking"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_hardcore_full"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_hardcore_full_blinking"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_hardcore_half"),
            ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/heart/overheated_hardcore_half_blinking")
    );

    private final ResourceLocation full;
    private final ResourceLocation fullBlinking;
    private final ResourceLocation half;
    private final ResourceLocation halfBlinking;
    private final ResourceLocation hardcoreFull;
    private final ResourceLocation hardcoreFullBlinking;
    private final ResourceLocation hardcoreHalf;
    private final ResourceLocation hardcoreHalfBlinking;

    private HeartType(
            final ResourceLocation p_300867_,
            final ResourceLocation p_300697_,
            final ResourceLocation p_297618_,
            final ResourceLocation p_298356_,
            final ResourceLocation p_300264_,
            final ResourceLocation p_299924_,
            final ResourceLocation p_297755_,
            final ResourceLocation p_298658_
    ) {
        this.full = p_300867_;
        this.fullBlinking = p_300697_;
        this.half = p_297618_;
        this.halfBlinking = p_298356_;
        this.hardcoreFull = p_300264_;
        this.hardcoreFullBlinking = p_299924_;
        this.hardcoreHalf = p_297755_;
        this.hardcoreHalfBlinking = p_298658_;
    }

    public ResourceLocation getSprite(boolean p_297692_, boolean p_299675_, boolean p_299889_) {
        if (!p_297692_) {
            if (p_299675_) {
                return p_299889_ ? this.halfBlinking : this.half;
            } else {
                return p_299889_ ? this.fullBlinking : this.full;
            }
        } else if (p_299675_) {
            return p_299889_ ? this.hardcoreHalfBlinking : this.hardcoreHalf;
        } else {
            return p_299889_ ? this.hardcoreFullBlinking : this.hardcoreFull;
        }
    }

    public static HeartType forPlayer(Player p_168733_) {
        HeartType gui$hearttype;
        if (p_168733_.hasEffect(MobEffects.POISON)) {
            gui$hearttype = POISIONED;
        } else if (p_168733_.hasEffect(MobEffects.WITHER)) {
            gui$hearttype = WITHERED;
        } else if (p_168733_.isFullyFrozen()) {
            gui$hearttype = FROZEN;
        } else if (p_168733_.hasEffect(RMobEffectRegister.EFFECT_HOT_BLUR.getHolder().orElseThrow())) {
            gui$hearttype = OVERHEATED;
        } else {
            gui$hearttype = NORMAL;
        }

        return gui$hearttype;
    }
}
