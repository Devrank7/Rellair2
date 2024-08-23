package org.rellair2.com.temperature.manager.valuable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.IValuableManager;
import org.rellair2.com.api.valuable.IValuable;
import org.rellair2.com.api.networking.IValuablePacket;

import java.util.List;
import java.util.function.Function;

public class TestManagerValuable extends IValuableManager<String, ITemperatureHandler<String>> {

    public TestManagerValuable(Player player) {
        super(player);
    }

    @Override
    protected void frequencyTick() {
        // empty
    }

    @Override
    protected Function<String, IValuablePacket<String>> getPacket() {
        return null;
    }

    @Override
    public IValuable<String> getValuable() {
        return new IValuable<String>() {
            @Override
            public void setValue(String value) {
                //System.err.println("NONE");
            }

            @Override
            public String getValue() {
                return "I am a valuable";
            }
        };
    }

    @Override
    protected String saveName() {
        return "test";
    }

    @Override
    public void readValue(CompoundTag compoundTag) {

    }

    @Override
    public void writeValue(CompoundTag compoundTag) {

    }

    @Override
    protected List<ITemperatureHandler<String>> getHandlers() {
        return List.of();
    }
}
