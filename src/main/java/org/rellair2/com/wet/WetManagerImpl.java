package org.rellair2.com.wet;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class WetManagerImpl extends WetManager {

    @Override
    protected float updateWetness(Player player, float humidity, float temperature,float temperaturePlayer) {

        return waterUpdateSystem(player, humidity, temperature,temperaturePlayer);
    }

    public float waterUpdateSystem(Player player, float falloff, float temperature,float temperaturePlayer) {
        float def = 1 + Math.max(0,temperature - temperaturePlayer) / 20f;
        falloff = falloff * 0.01f;
        float min = Math.max(0, falloff - 0.45f) * 1818;
        if (player.isInWaterOrBubble()) {
            return 4000;
        } else if (player.isInWaterRainOrBubble()) {
           float s =  (player.level().isThundering() ? 3 : 2);
            return Mth.clamp( wetness + s,1000,1001 * s);
        } else {
            return Math.max(min, wetness - (Math.abs(falloff - 1) * 2 * def));
        }
    }
}
