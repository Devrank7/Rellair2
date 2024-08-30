package org.rellair2.com.api.temperature;

import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.temperature.handlers.deal.Damage;

import java.util.List;

public interface IServerTemperatureType extends IObserverTick {

    public List<MobEffectInstance> getNegativeEffects();
    public Damage damage(DamageSources damageSources);

}
