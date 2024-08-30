package org.rellair2.com.api.block.strtegy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.rellair2.com.api.block.IThermalBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThermalStrategyImpl implements IThermalStrategy {

    @Override
    public float calculateExtraTemperature(Player player, List<IThermalBlock> thermalBlocks) {
        BlockPos playerPos = player.blockPosition();
        List<BlockParam> list = new ArrayList<>();
        int radius = 20;
        for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-radius, -radius, -radius), playerPos.offset(radius, radius, radius))) {
            Block block = player.level().getBlockState(pos).getBlock();
            for (IThermalBlock thermalBlock : thermalBlocks) {
                if (thermalBlock.block().equals(block)) {
                    double distance = playerPos.distSqr(pos);
                    if (distance <= thermalBlock.startRadius() * thermalBlock.startRadius()) {
                        float distanceFactor = (float) (1.0 - Math.sqrt(distance) / thermalBlock.startRadius());
                        float temp = thermalBlock.temperatureMax() * distanceFactor;
                        float temperatureContribution = applyBlockInterference(player, playerPos, pos, temp);
                        list.add(new BlockParam(thermalBlock, revThermalConductivity(list, temperatureContribution)));
                    }
                }
            }
        }
        return calculateTotalInfluence(list);
    }

    private float revThermalConductivity(List<BlockParam> blockParams, float conductivity) {
        if (blockParams.stream().map(BlockParam::conductivity).toList().contains(conductivity)) {
            return revThermalConductivity(blockParams, conductivity - 0.01f);
        } else {
            return conductivity;
        }
    }

    private float applyBlockInterference(Player player, BlockPos playerPos, BlockPos blockPos, float temperatureContribution) {
        if (IThermalStrategy.applyVec(player, playerPos, blockPos).getType() != BlockHitResult.Type.MISS) {
            temperatureContribution *= 0.5f;
        }
        return Math.max(temperatureContribution, 0);
    }

    private float calculateTotalInfluence(List<BlockParam> influences) {
        if (influences.isEmpty()) return 0;
        float maxPositive = 0;
        float maxNegative = 0;
        float maxPos = 0;
        float maxNeg = 0;
        for (BlockParam key : influences) {
            if (key.conductivity > 0) {
                maxPositive = Math.max(maxPositive, key.conductivity);
                maxPos = maxPositive * key.thermalBlock.speedThermalConductivity();
            } else if (key.conductivity < 0) {
                maxNegative = Math.min(maxNegative, key.conductivity);
                maxNeg = maxNegative * key.thermalBlock.speedThermalConductivity();
            }
        }
        float totalPos = maxPos;
        float totalNeg = maxNeg;
        int i = 0;
        for (BlockParam key : influences) {
            float influence = key.conductivity;
            if (influence > 0 && influence != maxPositive) {
                totalPos += influence * 0.5f * (influence / maxPositive) * key.thermalBlock.speedThermalConductivity();
                i++;
            } else if (influence < 0 && influence != maxNegative) {
                totalNeg += influence * 0.5f * (influence / maxNegative) * key.thermalBlock.speedThermalConductivity();
            }
        }
        return totalNeg + totalPos;
    }

    record BlockParam(IThermalBlock thermalBlock, float conductivity) {}
}
