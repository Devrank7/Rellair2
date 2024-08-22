package org.rellair2.com.temperature.manager.valuable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.manager.valuable.IValuableManager;
import org.rellair2.com.api.valuable.IValuable;
import org.rellair2.com.network.packet.ChangeTemperature;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.temperature.handlers.TemperatureChangeHandler;
import org.rellair2.com.temperature.TemperatureValuable;
import org.rellair2.com.temperature.handlers.TemperatureEffectHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TemperatureDataValuable extends IValuableManager<Float> {

    IValuable<Float> valuable = new TemperatureValuable<>(0f);

    public TemperatureDataValuable(Player player) {
        super(player);
    }

    @Override
    protected void frequencyTick() {
        System.err.println("Tick = " + valuable.getValue());
    }

    @Override
    @NotNull
    protected Function<Float, IValuablePacket<Float>> getPacket() {
        return ChangeTemperature::new;
    }

    @Override
    public IValuable<Float> getValuable() {
        return this.valuable;
    }

    @Override
    protected String saveName() {
        return "temperature";
    }

    @Override
    public void readValue(CompoundTag compoundTag) {
        valuable.setValue(compoundTag.getFloat(saveName()));
    }

    @Override
    public void writeValue(CompoundTag compoundTag) {
        compoundTag.putFloat(saveName(), valuable.getValue());
    }

    @Override
    protected List<IHandler<Float>> getHandlers() {
        return new ArrayList<>(List.of(
                new TemperatureChangeHandler(),
                new TemperatureEffectHandler()
        ));
    }
}
