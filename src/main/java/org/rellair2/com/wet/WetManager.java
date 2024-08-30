package org.rellair2.com.wet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public abstract class WetManager {

    protected float wetness = 0f;

    public void update(Player player, float humidity, float temperature,float temperatureForPlayer) {
        this.wetness = updateWetness(player,humidity, temperature,temperatureForPlayer);
    }

    public float getWetness() {
        return wetness;
    }

    public void setWetness(float wetness) {
        this.wetness = wetness;
    }

    public void read(CompoundTag tag) {
        wetness = tag.getFloat("wetness");
    }

    public void write(CompoundTag tag) {
        tag.putFloat("wetness", wetness);
    }
    public void copyFrom(WetManager other) {
        this.wetness = other.getWetness();
    }

    protected abstract float updateWetness(Player player,float humidity, float temperature,float temperatureForPlayer);
}
