package org.rellair2.com.api.manager.valuable.temperature;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.handlers.IHandler;
import org.rellair2.com.api.manager.valuable.IValuableManager;
import org.rellair2.com.api.mixins.IRPlayer;
import org.rellair2.com.network.ModMessage;
import org.rellair2.com.network.packet.BiomeHumidPack;
import org.rellair2.com.network.packet.BiomeTemperaturePack;
import org.rellair2.com.network.packet.SpeedBiomePack;
import org.rellair2.com.network.packet.WorldTemperaturePack;
import org.rellair2.com.wet.WetManager;
import org.rellair2.com.temperature.handlers.ICalculateHandler;
import org.rellair2.com.temperature.handlers.deal.IStateHandler;

public abstract class ITemperatureValuable<T, V extends IHandler<T>> extends IValuableManager<T, V> {
    public ITemperatureValuable(Player player) {
        super(player);
    }

    @Override
    public void tick() {
        super.tick();
        if (player.level().getGameTime() % frequency == 0) {
            ModMessage.sendToPlayerValuable(new WorldTemperaturePack(temperatureDirection().temperatureDirection), (ServerPlayer) player);
            ModMessage.sendToPlayerValuable(new BiomeTemperaturePack(temperatureDirection().temperatureOfBiome), (ServerPlayer) player);
            ModMessage.sendToPlayerValuable(new BiomeHumidPack(temperatureDirection().humidityOfBiome), (ServerPlayer) player);
            ModMessage.sendToPlayerValuable(new SpeedBiomePack(temperatureDirection().speed), (ServerPlayer) player);
            getValuable().setValue(calculateHandler().calculate(getValuable().getValue(), temperatureDirection().temperatureDirection, temperatureDirection().speed,((IRPlayer)player).getActivityManager().getActivityLevel()));
            getStateHandler().handle(this.player, getValuable().getValue());
        }
    }

    public abstract TemperatureDirection temperatureDirection();

    public abstract ICalculateHandler<T> calculateHandler();

    public abstract IStateHandler<T> getStateHandler();
    public abstract WetManager wetManager();

    public abstract void setStateHandler(IStateHandler<T> stateHandler);

    public static class TemperatureDirection {
        private float temperatureDirection;
        private float temperatureOfBiome;
        private float humidityOfBiome;
        private float climateWetness;
        private float speed;

        public TemperatureDirection(float temperatureDirection, float speed) {
            this.temperatureDirection = temperatureDirection;
            this.speed = speed;
        }

        public float getTemperatureDirection() {
            return temperatureDirection;
        }

        public float getSpeed() {
            return speed;
        }

        public void setTemperatureDirection(float temperatureDirection) {
            this.temperatureDirection = temperatureDirection;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getTemperatureOfBiome() {
            return temperatureOfBiome;
        }

        public void setTemperatureOfBiome(float temperatureOfBiome) {
            this.temperatureOfBiome = temperatureOfBiome;
        }

        public float getHumidityOfBiome() {
            return humidityOfBiome;
        }

        public void setHumidityOfBiome(float humidityOfBiome) {
            this.humidityOfBiome = humidityOfBiome;
        }

        public float getClimateWetness() {
            return climateWetness;
        }

        public void setClimateWetness(float climateWetness) {
            this.climateWetness = climateWetness;
        }
    }
}
