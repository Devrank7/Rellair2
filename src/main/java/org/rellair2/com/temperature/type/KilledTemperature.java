package org.rellair2.com.temperature.type;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import org.rellair2.com.api.gui.AdvanceGuiData;
import org.rellair2.com.api.gui.IGuiSimple;
import org.rellair2.com.api.temperature.IClientTemperatureType;
import org.rellair2.com.api.temperature.shake.IShake;
import org.rellair2.com.temperature.handlers.deal.Damage;
import org.rellair2.com.temperature.shake.ShakeImpl;

import java.util.List;

public class KilledTemperature extends UnkilledTemperature {


    public KilledTemperature(float start, List<MobEffectInstance> negativeEffects, ResourceLocation location,ResourceLocation location3,ResourceLocation location2,List<ResourceLocation> extraTextures) {
        super(start, negativeEffects, location, location2,location3, 100);
        for (int i = 0; i < extraTextures.size(); i++) {
            guiDraws.put("screen" + (i + 1), new AdvanceGuiData(0, 0, 0, 0, extraTextures.get(i), 100));
        }
    }

    protected IShake shake = new ShakeImpl();

    @Override
    protected void frequencyTick() {
        super.frequencyTick();
        guiDraws.values().forEach(p -> p.setStrength(Math.min(max_strength, start_tick)));
    }

    @Override
    public Damage damage(DamageSources damageSource) {
        return new Damage(1, damageSource.starve(), (p) -> p.level().getGameTime() % 80 == 0 && p.getRandom().nextBoolean());
    }

    @Override
    public IClientTemperatureType normalize(int x, int y) {
        IGuiSimple guiSimple = shake.shake(x, y);
        int xo = guiSimple.getX();
        int yo = guiSimple.getY();
        // guiDraws.keySet().stream().filter(p -> !p.equals("main")).toList().forEach(p -> guiDraws.get(p).setWidth(480).setHeight(320).setX(xo / 2 - 240).setY(yo - 300));
        // guiDraws.keySet().stream().filter(p -> !p.equals("main")).toList().forEach(p -> guiDraws.get(p).setWidth(0).setHeight(0).setX(0).setY(0));
        mainData.setWidth(16).setHeight(16).setX(xo / 2 - 8).setY(yo - 54);
        return this;
    }

    @Override
    public IClientTemperatureType copy(int x, int y) {
        return new KilledTemperature(start_temperature, negativeEffects, mainSetTexture2,guiDraws.get("screen").getLocation(), mainSetTexture,
                guiDraws.keySet().stream().
                        filter(v -> !v.equals("screen"))
                        .map(o -> guiDraws.get(o).getLocation()).toList()).setStartTime(100).normalize(x, y);
    }
}
