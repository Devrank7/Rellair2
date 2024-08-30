package org.rellair2.com.temperature.type;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import org.rellair2.com.api.gui.AdvanceGuiData;
import org.rellair2.com.api.temperature.IClientTemperatureType;
import org.rellair2.com.api.temperature.ITemperatureType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnkilledTemperature extends ITemperatureType {

    protected int max_strength = 100;
    protected Map<String, AdvanceGuiData> guiDraws = new HashMap<>();

    public UnkilledTemperature(float start, List<MobEffectInstance> negativeEffects, ResourceLocation location, ResourceLocation location3, ResourceLocation location2, int max_strength) {
        super(start, negativeEffects, location, location3);
        guiDraws.put("screen", new AdvanceGuiData(0, 0, 0, 0, location2, 0));
        this.max_strength = max_strength;
    }

    @Override
    protected void frequencyTick() {
        guiDraws.get("screen").setStrength(Math.min(max_strength, start_tick));
    }

    @Override
    public IClientTemperatureType copy(int x, int y) {
        return new UnkilledTemperature(start_temperature, negativeEffects, mainSetTexture2, mainSetTexture, guiDraws.get("screen").getLocation(), max_strength).setStartTime(100).normalize(x, y);
    }

    @Override
    public Map<String, AdvanceGuiData> getMics() {
        return guiDraws;
    }

    @Override
    public List<AdvanceGuiData> getEndGuiDraws(float f) {
        return guiDraws.values().stream().map(p -> {
            // System.err.println("location = " + p.getLocation());
            return p.setStrength((int) f);
        }).toList();
    }

    @Override
    public float getMaxTime() {
        return max_strength;
    }
}
