package org.rellair2.com.temperature.type;

import net.minecraft.resources.ResourceLocation;
import org.rellair2.com.api.gui.AdvanceGuiData;
import org.rellair2.com.api.temperature.IClientTemperatureType;
import org.rellair2.com.api.temperature.ITemperatureType;

import java.util.List;
import java.util.Map;

public class NormalTemperatyreType extends ITemperatureType {

    public NormalTemperatyreType(float s, ResourceLocation location, ResourceLocation location2) {
        super(s, List.of(), location, location2);
    }

    @Override
    protected void frequencyTick() {

    }

    @Override
    public IClientTemperatureType copy(int x, int y) {
        return new NormalTemperatyreType(start_temperature, mainSetTexture, mainSetTexture2).setStartTime(100).normalize(x, y);
    }

    @Override
    public List<AdvanceGuiData> getEndGuiDraws(float f) {
        return List.of();
    }

    @Override
    public Map<String, AdvanceGuiData> getMics() {
        return Map.of();
    }
}
