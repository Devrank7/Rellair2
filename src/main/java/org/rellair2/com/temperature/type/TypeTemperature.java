package org.rellair2.com.temperature.type;

public enum TypeTemperature {
    C,
    F(1.8f,0),
    K(1f,273.15f);
    private final float multi;
    private final float offset;

    TypeTemperature() {
        this(1f,0);
    }

    TypeTemperature(float multi, float offset) {
        this.multi = multi;
        this.offset = offset;
    }

    public float getMulti() {
        return multi;
    }

    public float getOffset() {
        return offset;
    }
}
