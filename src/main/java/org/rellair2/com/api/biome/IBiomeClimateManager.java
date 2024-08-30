package org.rellair2.com.api.biome;

import org.rellair2.com.api.biome.random.IRandomOffset;
import org.rellair2.com.api.biome.type.IBiomeType;
import org.rellair2.com.biome.RiverBiomeClimateSettings;
import org.rellair2.com.biome.SimpleBiomeClimateSettings;
import org.rellair2.com.biome.StaticBiomeClimateSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class IBiomeClimateManager {

    protected Map<IBiomeType, IBiomeClimateSettingsBase> biomeClimateSettings = new HashMap<>();

    public IBiomeClimateManager() {
        init();
    }

    public Map<IBiomeType,IBiomeClimateSettingsBase> getBiomeClimateSettings() {
        return biomeClimateSettings;
    }
    protected void putStatic(IBiomeType key, float temperature, float rainfall) {
        biomeClimateSettings.put(key, new StaticBiomeClimateSettings(temperature, rainfall));
    }

    protected void put(IBiomeType key, BiFunction<ClimateSettings, Function<Long, IRandomOffset>, SimpleBiomeClimateSettings> biFunction, float temperature, float rainfall) {
        biomeClimateSettings.put(key, biFunction.apply(new ClimateSettings(temperature, 20.0f, rainfall), null));
    }

    protected void put(IBiomeType key, BiFunction<ClimateSettings, Function<Long, IRandomOffset>, SimpleBiomeClimateSettings> biFunction, float temperature, float temperatureOffset, float rainfall) {
        biomeClimateSettings.put(key, biFunction.apply(new ClimateSettings(temperature, temperatureOffset, rainfall), null));
    }

    protected void putComplex(IBiomeType key, BiFunction<ClimateSettings, Function<Long, IRandomOffset>, SimpleBiomeClimateSettings> biFunction, float temperature, float temperatureOffset, float rainfall, Function<Long, IRandomOffset> function) {
        biomeClimateSettings.put(key, biFunction.apply(new ClimateSettings(temperature, temperatureOffset, rainfall), function));
    }
    protected void putComplex(IBiomeType key, BiFunction<ClimateSettings, Function<Long, IRandomOffset>, SimpleBiomeClimateSettings> biFunction, float temperature, float rainfall, Function<Long, IRandomOffset> function) {
        biomeClimateSettings.put(key, biFunction.apply(new ClimateSettings(temperature, 20.0f, rainfall), function));
    }
    protected void putRiver(IBiomeType key,float humidity, float temperature) {
        biomeClimateSettings.put(key, new RiverBiomeClimateSettings(humidity, temperature,this));
    }
    protected abstract void init();
}
