package org.rellair2.com.temperature.type;

import net.minecraft.util.valueproviders.UniformFloat;

import java.util.Arrays;

public class TypeTemperature {
    public enum Type {
        C, F
    }

    public enum TempType {
        ICY(UniformFloat.of(-11,-7)),
        BITTER_COLD(UniformFloat.of(-7,-5)),
        COLD(UniformFloat.of(-5,-3)),
        CHILLY(UniformFloat.of(-3,-1)),
        NORMAL(UniformFloat.of(-1,1)),
        LITTLE_WARM(UniformFloat.of(1,3)),
        WARM(UniformFloat.of(3,5)),
        VERY_WARM(UniformFloat.of(5,7)),
        HOT(UniformFloat.of(7,11)),;

        private UniformFloat uniformFloat;

        TempType(UniformFloat uniformFloat) {
            this.uniformFloat = uniformFloat;
        }
        public static TempType findBy(float temperature) {
            return Arrays.stream(values()).filter(p -> p.uniformFloat.getMinValue() <= temperature && p.uniformFloat.getMaxValue() >= temperature).findFirst().orElse(TempType.ICY);
        }
    }
}
