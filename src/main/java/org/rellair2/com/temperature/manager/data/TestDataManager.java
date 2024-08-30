package org.rellair2.com.temperature.manager.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.manager.data.IDataManager;
import org.rellair2.com.api.manager.valuable.IValuableManager;
import org.rellair2.com.network.CodeObject;
import org.rellair2.com.temperature.handlers.deal.IStateHandler;

import java.util.Map;

public class TestDataManager<V extends IValuableManager<?,?>> extends IDataManager<V> {

    public Map<Class<? extends V>, V> classVMap;

    public TestDataManager(Player player, Map<Class<? extends V>, V> classVMap) {
        super(player);
        this.classVMap = classVMap;
    }

    @Override
    public void init() {

    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36215_) {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36265_) {

    }

    @Override
    public void copyFrom(IDataManager<?> p_36221_) {

    }

    @Override
    public Map<Class<? extends V>, V> valuableManagers() {
        return Map.of();
    }
}
