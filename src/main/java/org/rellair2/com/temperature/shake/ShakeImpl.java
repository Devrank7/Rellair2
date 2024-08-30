package org.rellair2.com.temperature.shake;

import org.rellair2.com.api.gui.GuiSimple;
import org.rellair2.com.api.gui.IGuiSimple;
import org.rellair2.com.api.temperature.shake.IShake;

import java.util.Random;

public class ShakeImpl implements IShake {

    private Random RANDOM = new Random();

    @Override
    public IGuiSimple shake(int x, int y) {
        if (RANDOM.nextBoolean()) {
            int yo = y + (int) ((RANDOM.nextInt(3) - 1) * Math.min(3F, 1.0));
            int xo = x + (int) ((RANDOM.nextInt(3) - 1) * Math.min(1.5F, 1.0));
            return new GuiSimple(xo, yo);
        }
        return new GuiSimple(x, y);
    }
}
