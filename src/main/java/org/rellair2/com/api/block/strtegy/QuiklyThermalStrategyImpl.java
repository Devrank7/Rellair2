package org.rellair2.com.api.block.strtegy;

import com.google.common.util.concurrent.AtomicDouble;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.rellair2.com.api.block.IThermalBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToDoubleFunction;

public class QuiklyThermalStrategyImpl implements IThermalStrategy {

    @Override
    public float calculateExtraTemperature(Player player, List<IThermalBlock> thermalBlocks) {
        BlockPos playerPos = player.blockPosition();
        List<BlockParam> blockParams = new ArrayList<>();
        int radius = 20;
        BlockPos.betweenClosedStream(playerPos.offset(-radius, -radius, -radius), playerPos.offset(radius, radius, radius))
                .map(pos -> Map.entry(pos, player.level().getBlockState(pos).getBlock())).filter(entry -> thermalBlocks.stream().anyMatch(tb -> tb.block().equals(entry.getValue()))).forEach(entry -> {
                    BlockPos pos = entry.getKey();
                    Block block = entry.getValue();
                    thermalBlocks.stream().filter(thermalBlock -> thermalBlock.block().equals(block)).forEach(thermalBlock -> {
                        double distance = playerPos.distSqr(pos);
                        if (distance <= thermalBlock.startRadius() * thermalBlock.startRadius()) {
                            float distanceFactor = (float) (1.0 - Math.sqrt(distance) / thermalBlock.startRadius());
                            float temp = thermalBlock.temperatureMax() * distanceFactor;
                            float temperatureContribution = applyBlockInterference(player, playerPos, pos, temp);
                            blockParams.add(new BlockParam(thermalBlock, adjustConductivity(blockParams, temperatureContribution)));
                        }
                    });
                });
        return calculateTotalInfluence(blockParams);
    }

    private float adjustConductivity(List<BlockParam> blockParams, float conductivity) {
        if (blockParams.stream().map(BlockParam::conductivity).toList().contains(conductivity)) {
            return adjustConductivity(blockParams, conductivity - 0.01f);
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
        AtomicDouble i_max = new AtomicDouble(0.55D);
        AtomicDouble i_min = new AtomicDouble(0.55D);
        AtomicInteger i = new AtomicInteger(10);
        float maxPositive = (float) influences.stream().filter(bp -> bp.conductivity > 0).mapToDouble(BlockParam::conductivity).max().orElse(0);
        float maxNegative = (float) influences.stream().filter(bp -> bp.conductivity < 0).mapToDouble(BlockParam::conductivity).min().orElse(0);
        ToDoubleFunction<BlockParam> calculateInfluence = bp -> {
            float influence = bp.conductivity;
            AtomicDouble currentI = influence > 0 ? i_max : i_min;
            float maxConductivity = influence > 0 ? maxPositive : maxNegative;
            currentI.set(currentI.get() - (currentI.get() / Math.max(1d, i.get())));
            i.decrementAndGet();
            return influence == maxConductivity ? maxConductivity * bp.thermalBlock.speedThermalConductivity() : influence * Math.max(0D, currentI.get()) * (influence / maxConductivity) * bp.thermalBlock.speedThermalConductivity();
        };
        float totalPos = (float) influences.stream().filter(bp -> bp.conductivity > 0).mapToDouble(calculateInfluence).sum();
        float totalNeg = (float) influences.stream().filter(bp -> bp.conductivity < 0).mapToDouble(calculateInfluence).sum();
        return totalPos + totalNeg;
    }

    private record BlockParam(IThermalBlock thermalBlock, float conductivity) {
    }
}
