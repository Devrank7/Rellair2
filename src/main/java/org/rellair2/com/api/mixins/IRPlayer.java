package org.rellair2.com.api.mixins;

import org.rellair2.com.api.manager.data.IDataManager;

import java.util.Map;

public interface IRPlayer {

    public Map<Class<? extends IDataManager>, IDataManager> getAllData();
    public <T extends IDataManager> T getData(Class<T> clazz);
    public void copyData(IRPlayer player);
}
