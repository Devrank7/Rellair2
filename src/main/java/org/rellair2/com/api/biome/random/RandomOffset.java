package org.rellair2.com.api.biome.random;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;


public class RandomOffset implements IRandomOffset {

    private final RandomSource random;
    private boolean isIncreasing;
    private final float[] temperatureOffsets;

    public RandomOffset(long seed) {
        this.random = RandomSource.create(seed);
        this.isIncreasing = true;
        System.err.println("s");
        this.temperatureOffsets = generateTemperatureOffsets(1000);
    }
    public float[] generateTemperatureOffsets(int days) {
        float[] temperatureOffsets = new float[days];
        float currentTemperature = 0.0f;
        int stableDays = 0;
        int transitionDays = 0;

        for (int i = 0; i < days; i++) {
            if (stableDays > 0) {
                // Стабильный период
                stableDays--;
                currentTemperature += (random.nextFloat() * 2.0f) - 1.0f; // колебание от -1 до 1
                currentTemperature = Math.max(-10.0f, Math.min(10.0f, currentTemperature)); // Ограничение на диапазон
            } else if (transitionDays > 0) {
                // Переходный период
                transitionDays--;
                if (isIncreasing) {
                    currentTemperature += random.nextFloat() * 3.0f; // Положительное смещение
                } else {
                    currentTemperature -= random.nextFloat() * 3.0f; // Отрицательное смещение
                }
                currentTemperature = Math.max(-10.0f, Math.min(10.0f, currentTemperature)); // Ограничение на диапазон
            } else {
                // Определение нового периода
                stableDays = random.nextInt(9) + 2; // Стабильный период от 2 до 10 дней
                transitionDays = random.nextInt(4) + 2; // Переходный период от 2 до 5 дней
                isIncreasing = random.nextBoolean(); // Определение нового направления
            }

            temperatureOffsets[i] = currentTemperature;
        }

        return temperatureOffsets;
    }



    @Override
    public float offset(int day) {
        return temperatureOffsets[day];
    }
}
