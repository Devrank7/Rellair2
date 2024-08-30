package org.rellair2.com.api.temperature.fsm;

import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.api.gui.AdvanceGuiData;
import org.rellair2.com.api.temperature.IArrowDrawer;
import org.rellair2.com.api.temperature.IClientTemperatureType;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateMachine<T extends IClientTemperatureType & IArrowDrawer> implements IObserverTick {

    private T type = null;
    private final List<TypeOfTime> old_types = new ArrayList<>();

    public T setIfOther(@NotNull T newType, int x, int y, final float currentTemperature) {
        if (type == null || !type.equals(newType)) {
            // System.err.println("type = " + type + ", newType = " + newType);
            if (currentTemperature == TemperatureDataValuable.INITIAL_TEMPERATURE) return null;
            if (type != null) {
                type.reset();
                old_types.add(new TypeOfTime((int) type.getMaxTime(), (T) type.copy(x, y)));
            }
            float s1 = newType.getStartTemperature();
            float s2 = type == null ? 0 :type.getStartTemperature();
            type = newType;
            type.setDirection(s1 > s2 ? IArrowDrawer.ArrowDirection.UP : IArrowDrawer.ArrowDirection.DOWN);
        }
        return type;
    }

    public T getType() {
        return type;
    }

    @Override
    public void tick() {
        Optional.ofNullable(type).ifPresent(IObserverTick::tick);
        old_types.forEach(p -> p.end_time--);
        old_types.removeIf(p -> {
            return p.end_time <= 0;
        });
    }

    public List<AdvanceGuiData> lastDraws() {
        return this.old_types.stream().flatMap(p -> p.type.getEndGuiDraws(p.end_time).stream()).toList();
    }

    class TypeOfTime {
        private int end_time;
        private final T type;

        public TypeOfTime(int end_time, T type) {
            this.end_time = end_time;
            this.type = type;
        }
    }
}
