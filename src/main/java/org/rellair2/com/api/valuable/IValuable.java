package org.rellair2.com.api.valuable;

public interface IValuable<T> {
    public void setValue(T value);

    public T getValue();
}
