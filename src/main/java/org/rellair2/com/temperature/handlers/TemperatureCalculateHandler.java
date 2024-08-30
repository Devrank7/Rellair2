package org.rellair2.com.temperature.handlers;

import net.minecraft.util.Mth;
import org.rellair2.com.Config;

public class TemperatureCalculateHandler implements ICalculateHandler<Float> {

    @Override
    public Float calculate(Float value, float direct_temperature, float speed, float activity) {
        speed *= 0.05f;
        speed *= Config.Temperature.speedModifier;
        if (Config.Activity.applyActivity) {
            direct_temperature += activity;
        }
        float delta = calculateTemperature(value, direct_temperature, speed);
        // System.err.println("Delta = " + (delta * 10) + " speed = " + (speed * 10));
        return delta;
    }

    protected float calculateTemperature(float currentPlayerTemperature, float currentTemperatureForBiome, float speed) {
        float difference = Math.abs(currentTemperatureForBiome - currentPlayerTemperature);
        float delta = quadraticFactor() * (float) Math.pow(difference, getPower(difference)) * speed;
        float delta_nor = Mth.clamp(delta, 0.025f, difference);
        return currentPlayerTemperature + (delta_nor * Math.signum(currentTemperatureForBiome - currentPlayerTemperature));
    }

    protected float quadraticFactor() {
        return 0.1f;
    }

    protected float getPower(float difference) {
        if (difference > 10) return 1f;
        return 1 + (10 - difference) / 20;
    }
}
