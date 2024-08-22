package org.rellair2.com.temperature;

import org.rellair2.com.api.valuable.IValuable;

public class TemperatureValuable<T> implements IValuable<T> {

    private T value;

    public TemperatureValuable(T value) {
        this.value = value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}
