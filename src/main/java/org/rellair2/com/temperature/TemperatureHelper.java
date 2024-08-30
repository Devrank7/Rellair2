package org.rellair2.com.temperature;

import org.rellair2.com.temperature.type.TypeTemperature;

public class TemperatureHelper {
    public static double getPlayerTemperature(float temperatureDirection) {
        // System.err.println("pow = " + Math.pow(24D - temperatureDirection, 1.45D));
        double a = temperatureDirection - 24D;
        double b = Math.abs(a);
        double c = Math.signum(a);
        return 36.6D - (Math.pow(b, 1.45D) * 0.05D * c);
    }
    public static float getTemperatureIn(float temperature,TypeTemperature type) {
        return temperature + type.getOffset() * type.getMulti();
    }
}
