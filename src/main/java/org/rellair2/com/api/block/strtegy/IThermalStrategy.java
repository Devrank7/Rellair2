package org.rellair2.com.api.block.strtegy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.rellair2.com.api.block.IThermalBlock;

import java.util.List;

public interface IThermalStrategy {
    public float calculateExtraTemperature(Player player, List<IThermalBlock> thermalBlocks);
    static BlockHitResult applyVec(Player player, BlockPos playerPos, BlockPos blockPos) {
        Vec3 startVec = new Vec3(playerPos.getX() + 0.5, playerPos.getY() + player.getEyeHeight(), playerPos.getZ() + 0.5);
        Vec3 endVec = new Vec3(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
        return player.level().clip(new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
    }
}
