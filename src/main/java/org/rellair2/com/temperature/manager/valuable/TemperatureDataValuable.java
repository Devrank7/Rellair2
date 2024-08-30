package org.rellair2.com.temperature.manager.valuable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.rellair2.com.api.biome.IBiomeClimateManager;
import org.rellair2.com.api.block.ThermalBlock;
import org.rellair2.com.api.block.strtegy.QuiklyThermalStrategyImpl;
import org.rellair2.com.api.gui.GuiDraw;
import org.rellair2.com.api.handlers.temperature.ITemperatureHandler;
import org.rellair2.com.api.manager.valuable.temperature.ITemperatureValuable;
import org.rellair2.com.api.networking.IValuablePacket;
import org.rellair2.com.api.valuable.IValuable;
import org.rellair2.com.network.packet.ChangeTemperature;
import org.rellair2.com.biome.BiomeClimateManagerImpl;
import org.rellair2.com.temperature.TemperatureValuable;
import org.rellair2.com.wet.WetManager;
import org.rellair2.com.wet.WetManagerImpl;
import org.rellair2.com.temperature.handlers.*;
import org.rellair2.com.temperature.handlers.deal.IStateHandler;
import org.rellair2.com.temperature.handlers.deal.TemperatureEffectHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TemperatureDataValuable extends ITemperatureValuable<Float, ITemperatureHandler<Float>> {
    public static final float INITIAL_TEMPERATURE = -0.67250994358340850935f;

    private final IValuable<Float> valuable = new TemperatureValuable<>(INITIAL_TEMPERATURE);
    private final List<ITemperatureHandler<Float>> list;
    private final TemperatureDirection temperatureDirection = new TemperatureDirection(0, 0);
    private final List<GuiDraw> guiDraws = new ArrayList<>();
    private final ICalculateHandler<Float> calculateHandler = new TemperatureCalculateHandler();
    private IStateHandler<Float> stateHandler = new TemperatureEffectHandler();
    private final WetManager wetManager = new WetManagerImpl();


    public TemperatureDataValuable(Player player) {
        super(player);
        IBiomeClimateManager biomeClimateManager = new BiomeClimateManagerImpl();
        list = new ArrayList<>(List.of(
                new TemperatureChooseHandler(this, biomeClimateManager),
                new TemperatureBlockHandler(this, new QuiklyThermalStrategyImpl(), List.of(
                        new ThermalBlock(Blocks.LAVA, 150, 10, 1f),
                        new ThermalBlock(Blocks.FIRE, 50, 6, 0.7f),
                        new ThermalBlock(Blocks.CAMPFIRE, 40, 5, 0.65f),
                        new ThermalBlock(Blocks.MAGMA_BLOCK, 30, 5, 0.5f),
                        new ThermalBlock(Blocks.SOUL_FIRE, -50, 5, 0.7f),
                        new ThermalBlock(Blocks.SOUL_CAMPFIRE, -40, 5, 0.65f),
                        new ThermalBlock(Blocks.TORCH, 10, 2, 0.2f),
                        new ThermalBlock(Blocks.SOUL_TORCH, -10, 2, 0.2f),
                        new ThermalBlock(Blocks.ICE, -2, 2, 0.2f),
                        new ThermalBlock(Blocks.SNOW, -2, 2, 0.2f),
                        new ThermalBlock(Blocks.SNOW_BLOCK, -2, 2, 0.2f),
                        new ThermalBlock(Blocks.POWDER_SNOW, -10, 2, 0.7f),
                        new ThermalBlock(Blocks.POWDER_SNOW_CAULDRON, -10, 2, 0.7f),
                        new ThermalBlock(Blocks.FROSTED_ICE, -4, 2, 0.25f),
                        new ThermalBlock(Blocks.BLUE_ICE, -5, 2, 0.25f)
                )),
                new WetHandler(this, biomeClimateManager, wetManager)
        ));
    }

    @Override
    protected void frequencyTick() {
        //System.err.println("Tick = " + valuable.getValue());
    }

    @Override
    public IStateHandler<Float> getStateHandler() {
        return stateHandler;
    }

    @Override
    public void setStateHandler(IStateHandler<Float> stateHandler) {
        this.stateHandler = stateHandler;
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
        wetManager.read(compoundTag);
    }

    @Override
    public void writeValue(CompoundTag compoundTag) {
        compoundTag.putFloat(saveName(), valuable.getValue());
        wetManager.write(compoundTag);
    }

    @Override
    protected List<ITemperatureHandler<Float>> getHandlers() {
        return list;
    }

    @Override
    public TemperatureDirection temperatureDirection() {
        return this.temperatureDirection;
    }

    @Override
    public ICalculateHandler<Float> calculateHandler() {
        return calculateHandler;
    }

    @Override
    public WetManager wetManager() {
        return wetManager;
    }
}
