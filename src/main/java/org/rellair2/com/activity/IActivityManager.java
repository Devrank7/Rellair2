package org.rellair2.com.activity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public interface IActivityManager {
    void updateActivityLevel(Player player, ActivityType type,float mult);

    void copyFrom(IActivityManager other);

    float getActivityLevel();
    void setActivityLevel(float activityLevel);
    void tick(Player player);

    void raedAdditionalData(CompoundTag tag);

    void writeAdditionalData(CompoundTag tag);

    enum ActivityType {
        SPRINTING,
        SWIMMING,
        CROUCHING,
        JUMPING,
        JUMPING_AND_SPRINTING,
        ATTACKING,
        MINING,
        IN_WATER,
        CLIMBING,
        DAMAGE_ACTUAL
    }
}
