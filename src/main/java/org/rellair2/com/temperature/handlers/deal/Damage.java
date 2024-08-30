package org.rellair2.com.temperature.handlers.deal;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.temperature.type.TypeTemperature;

import java.util.function.Predicate;


public record Damage(int amount, DamageSource damageSource, Predicate<LivingEntity> damagePredicate) {
    public void damage(LivingEntity entity) {
        if (damagePredicate.test(entity)) {
            entity.hurt(damageSource, amount);
        }
    }

}

