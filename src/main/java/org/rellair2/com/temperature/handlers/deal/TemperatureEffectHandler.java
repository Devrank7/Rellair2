package org.rellair2.com.temperature.handlers.deal;

import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.temperature.IServerTemperatureType;
import org.rellair2.com.api.temperature.TempType;

import java.util.List;

public class TemperatureEffectHandler implements IStateHandler<Float> {

    @Override
    public void handle(@NotNull Player player, final Float value) {
        var type = getTempType(value);
        getNegativeEffects(type).forEach(p -> player.addEffect(new MobEffectInstance(p)));
        damage(type, player.damageSources()).damage(player);
    }

    protected IServerTemperatureType getTempType(final float value) {
        return TempType.getServerType(value);
    }

    protected List<MobEffectInstance> getNegativeEffects(IServerTemperatureType type) {
        return type.getNegativeEffects();
    }

    protected Damage damage(IServerTemperatureType type, DamageSources damageSource) {
        return type.damage(damageSource);
    }
}
