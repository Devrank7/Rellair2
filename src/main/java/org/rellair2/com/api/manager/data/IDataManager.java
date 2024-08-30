package org.rellair2.com.api.manager.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.IObserverTick;
import org.rellair2.com.api.manager.valuable.IValuableManager;
import org.rellair2.com.temperature.handlers.deal.IStateHandler;

import java.util.Map;

public abstract class IDataManager<V extends IValuableManager<?,?>> implements IObserverTick {
    protected Player player;

    public IDataManager(Player player) {
        this.player = player;
        init();
    }

    @Override
    public void tick() {
        if (player.level().getGameTime() % 5 == 0) {

        }
    }

    public abstract void init();

    public abstract void readAdditionalSaveData(CompoundTag p_36215_);

    public abstract void addAdditionalSaveData(CompoundTag p_36265_);

    public abstract void copyFrom(IDataManager<?> p_36221_);

    @SuppressWarnings("unchecked")
    public <T extends IValuableManager<?,?>> T getData(Class<T> clazz) {
        return (T) valuableManagers().get(clazz);
    }

    public abstract Map<Class<? extends V>, V> valuableManagers();
}
