package org.rellair2.com.temperature.handlers;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.handlers.IHandler;

public class TemperatureEffectHandler implements IHandler<Float> {

    @Override
    public Float handle(Player player, Float value) {
        return 0f;
    }

    @Override
    public boolean needToChange() {
        return false;
    }
}
