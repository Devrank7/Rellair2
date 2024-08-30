package org.rellair2.com.activity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.network.ModMessage;
import org.rellair2.com.network.packet.ActivityPacket;

public class ActivityManager implements IActivityManager {
    private float activityLevel = 0f;

    public ActivityManager(float activityLevel) {
        this.activityLevel = activityLevel;
    }

    @Override
    public void updateActivityLevel(Player player, ActivityType type, float mult) {
        activityLevel += switch (type) {
            case SPRINTING -> 0.0025f;
            case SWIMMING -> 0.00225f;
            case JUMPING -> 0.06f;
            case CROUCHING, CLIMBING -> 0.0015f;
            case IN_WATER -> 0.002f;
            case MINING, ATTACKING -> 0.045f;
            case DAMAGE_ACTUAL -> 0.5f;
            case JUMPING_AND_SPRINTING -> 0.08f;
        } * mult;
    }

    @Override
    public void copyFrom(IActivityManager other) {
        activityLevel = other.getActivityLevel();
    }

    @Override
    public float getActivityLevel() {
        return this.activityLevel;
    }

    @Override
    public void raedAdditionalData(CompoundTag tag) {
        activityLevel = tag.getFloat("activityLevel");
    }

    @Override
    public void writeAdditionalData(CompoundTag tag) {
        tag.putFloat("activityLevel", activityLevel);
    }

    @Override
    public void tick(Player player) {
        activityLevel = Math.max(0, activityLevel - Math.max(0, 0.001f * (float) Math.pow(2, activityLevel / 5f)));
        if (player.level().getGameTime() % 10 == 0) {
            ModMessage.sendToPlayer(new ActivityPacket(activityLevel), (ServerPlayer) player);
        }
    }

    @Override
    public void setActivityLevel(float activityLevel) {
        this.activityLevel = activityLevel;
    }
}
