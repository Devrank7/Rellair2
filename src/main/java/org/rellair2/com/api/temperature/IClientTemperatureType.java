package org.rellair2.com.api.temperature;

import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.api.gui.AdvanceGuiData;

import java.util.List;
import java.util.Map;

public interface IClientTemperatureType extends IObserverTick {

    void reset();

    public AdvanceGuiData getMain();
    public List<AdvanceGuiData> getEndGuiDraws(float f);
    public Map<String,AdvanceGuiData> getMics();
    public IClientTemperatureType normalize(int x, int y);
    public IClientTemperatureType copy(int x, int y);
    public float getStartTemperature();
    default float getMaxTime() {
        return 100;
    }
}
