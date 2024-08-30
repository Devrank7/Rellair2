package org.rellair2.com.temperature.handlers;

import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.biome.type.BiomeType;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;
import org.rellair2.com.api.valuable.IValuable;
import org.rellair2.com.wet.WetManager;

public class WetHandler extends TemperatureHandler {
    private final WetManager wetUpdateSystem;

    public WetHandler(ITemperatureValuable<Float, ITemperatureHandler<Float>> temperatureValuable, IBiomeClimateManager climateManager, WetManager wetUpdateSystem) {
        super(temperatureValuable, climateManager);
        this.wetUpdateSystem = wetUpdateSystem;
    }

    @Override
    public void handle(Player player, IValuable<Float> value) {
        wetUpdateSystem.update(player,
                climateManager.getBiomeClimateSettings().
                        get(BiomeType.getBiome(player.level().getBiome(player.blockPosition()).
                                unwrapKey().orElseThrow())).
                        getBaseHumidity(),
                temperatureValuable.temperatureDirection().getTemperatureDirection(),
                32f);
    }
}
