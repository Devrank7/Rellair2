package org.rellair2.com.temperature.manager.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.manager.data.IDataManager;
import org.rellair2.com.api.manager.valuable.IValuableManager;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;
import org.rellair2.com.temperature.manager.valuable.TestManagerValuable;

import java.util.Map;

public class TemperatureDataManager extends IDataManager {


    public TemperatureDataManager(Player player) {
        super(player);
    }
    private Map<Class<? extends IValuableManager<?>>, IValuableManager<?>> dataManagers;

    @Override
    public void init() {
        dataManagers = Map.of(
                TemperatureDataValuable.class,new TemperatureDataValuable(this.player),
                TestManagerValuable.class, new TestManagerValuable(this.player)
        );
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36215_) {
        dataManagers.values().forEach(data -> data.readValue(p_36215_));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36265_) {
        dataManagers.values().forEach(data -> data.writeValue(p_36265_));
    }

    @Override
    public void copyFrom(IDataManager p_36221_) {
        for (IValuableManager<?> data : dataManagers.values()) {
            for (IValuableManager<?> data2 : p_36221_.valuableManagers().values()) {
                copyValuable(data, data2);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private <T> void copyValuable(IValuableManager<T> data1, IValuableManager<?> data2) {
        if (data1.getValuable().getValue().getClass().isAssignableFrom(data2.getValuable().getValue().getClass())) {
            data1.getValuable().setValue((T) data2.getValuable().getValue());
        }
    }

    @Override
    public Map<Class<? extends IValuableManager<?>>, IValuableManager<?>> valuableManagers() {
        return dataManagers;
    }
}
