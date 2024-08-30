package org.rellair2.com.api.temperature;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import org.rellair2.com.api.gui.AdvanceGuiData;
import org.rellair2.com.temperature.handlers.deal.Damage;

import java.util.List;

public abstract class ITemperatureType implements IClientTemperatureType, IServerTemperatureType, IArrowDrawer {

    protected AdvanceGuiData mainData;
    protected List<MobEffectInstance> negativeEffects;
    protected int start_tick;
    protected final float start_temperature;
    protected ResourceLocation mainSetTexture;
    protected ResourceLocation mainSetTexture2;
    protected ArrowDirection direction = ArrowDirection.UP;

    public ITemperatureType(float startTemperature, List<MobEffectInstance> negativeEffects, ResourceLocation location, ResourceLocation location3) {
        this.negativeEffects = ImmutableList.copyOf(negativeEffects);
        this.mainData = new AdvanceGuiData(
                0, 0, 0, 0,
                location3, 100);
        this.mainSetTexture = location3;
        this.mainSetTexture2 = location;
        this.start_temperature = startTemperature;
    }

    @Override
    public float getStartTemperature() {
        return start_temperature;
    }

    protected ITemperatureType setStartTime(int start_tick) {
        this.start_tick = start_tick;
        return this;
    }

    @Override
    public void reset() {
        start_tick = 0;
    }

    @Override
    public void tick() {
        mainData.setLocation(start_tick > 30 ? mainSetTexture2 : mainSetTexture);
        if (start_tick++ % 5 == 0) {
            frequencyTick();
        }
    }

    protected abstract void frequencyTick();

    @Override
    public AdvanceGuiData getMain() {
        return mainData;
    }

    public List<MobEffectInstance> getNegativeEffects() {
        return negativeEffects;
    }

    public Damage damage(DamageSources damageSources) {
        return new Damage(0, damageSources.starve(), (p) -> false);
    }

    @Override
    public IClientTemperatureType normalize(int x, int y) {
        mainData.setWidth(16).setHeight(16).setX(x / 2 - 8).setY(y - 54);
        return this;
    }

    @Override
    public int getDirectionIndex() {
        return Mth.clamp(start_tick / 2, 0, 15);
    }

    @Override
    public void setDirection(ArrowDirection direction) {
        this.direction = direction;
    }

    @Override
    public ArrowDirection getDirection() {
        return direction;
    }
}
