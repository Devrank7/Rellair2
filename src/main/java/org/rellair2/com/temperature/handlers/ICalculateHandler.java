package org.rellair2.com.temperature.handlers;

public interface ICalculateHandler<T> {
    public T calculate(T value,float direct_temperature,float speed,float activity);
}
