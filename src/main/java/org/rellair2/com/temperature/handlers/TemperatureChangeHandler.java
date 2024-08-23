package org.rellair2.com.temperature.handlers;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.ann.IsInteger;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;

public class TemperatureChangeHandler implements ITemperatureHandler<Float> {

    @Override
    public Float handle(Player player, Float value) {
        if (player.getRandom().nextInt(20) == 0) {
            float f = (float)player.getRandom().nextInt(0,12);
            System.err.println("Change Value = " + f);
            return f;
        }
        return value;
    }

    @Override
    public boolean needToChange() {
        return true;
    }
}
