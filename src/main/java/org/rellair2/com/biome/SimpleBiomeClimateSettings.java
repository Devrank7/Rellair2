package org.rellair2.com.biome;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.rellair2.com.api.biome.ClimateSettings;
import org.rellair2.com.api.biome.IBiomeClimateSettingsBase;
import org.rellair2.com.api.biome.random.IRandomOffset;
import org.rellair2.com.api.mixins.IRLevel;

import java.util.Arrays;
import java.util.function.Function;

public class SimpleBiomeClimateSettings implements IBiomeClimateSettingsBase {

    protected ClimateSettings climateSettings;
    protected final Function<Long, IRandomOffset> functionOffset;
    protected IRandomOffset methodOffset;

    public SimpleBiomeClimateSettings(ClimateSettings climateSettings, @Nullable Function<Long, IRandomOffset> functionOffset) {
        this.climateSettings = climateSettings;
        this.functionOffset = functionOffset;
    }

    @Override
    public float getTemperatureDaytime(Player player, float timeOfDay) {
        float abs_falloff = Math.abs(climateSettings.humidity() - 100.0f) * 0.01f;
        return climateSettings.temperature() + switch (TimeOfDay.getTimeOfDay(timeOfDay % 24000)) {
            case DAY -> climateSettings.temperatureOffset() * (abs_falloff + 0.1f);
            case NIGHT -> -(climateSettings.temperatureOffset() * (abs_falloff + 0.1f));
            default -> 0;
        } + complexFunctionOffset((ServerLevel) player.level(), timeOfDay);
    }

    protected float complexFunctionOffset(ServerLevel level, float timeOfDay) {
        if (functionOffset == null) return 0.0f;
        if (methodOffset == null) {
            methodOffset = functionOffset.apply(level.getSeed());
        }
        return methodOffset.offset(Mth.floor(timeOfDay / 24000L) % 1000);
    }

    @Override
    public float getHumidityDaytime(Player player, float timeOfDay) {
        IRLevel iRLevel = (IRLevel) (ServerLevel) player.level();
        float rain_or_thunder = ((float) Math.abs(Math.min(0L, iRLevel.rellair2$ticksSinceLastRain() - 4000L)) / 4000.0f) + 1.0f;
        return (100 + climateSettings.humidity()) * rain_or_thunder;
    }

    @Override
    public float getWaterTemperature(Player player, float timeOfDay) {
        float a = getBaseTemperature() - getTemperatureDaytime(player, timeOfDay);
        float ab = Math.signum(a);
        float abs = Math.abs(a);
        return getBaseTemperature() + ((abs / 10F) * -ab);
    }

    @Override
    public float getBaseTemperature() {
        return climateSettings.temperature();
    }

    @Override
    public float getBaseHumidity() {
        return climateSettings.humidity();
    }

    enum TimeOfDay {
        MORNING(22500),
        DAY(3000),
        EVENING(10000),
        NIGHT(15500);
        private final int start_time;

        TimeOfDay(int start_time) {
            this.start_time = start_time;
        }

        public static TimeOfDay getTimeOfDay(float dayTime) {
            return Arrays.stream(TimeOfDay.values()).filter(time -> time == MORNING ? (dayTime >= time.start_time || dayTime < DAY.start_time) : (dayTime >= time.start_time && dayTime < time.getNext().start_time)).findFirst().orElseThrow();
        }

        private TimeOfDay getNext() {
            return this.ordinal() < TimeOfDay.values().length - 1 ? TimeOfDay.values()[this.ordinal() + 1] : MORNING;
        }
    }
}
