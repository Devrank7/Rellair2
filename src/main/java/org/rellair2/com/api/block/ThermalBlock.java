package org.rellair2.com.api.block;

import net.minecraft.world.level.block.Block;

import java.util.List;

public record ThermalBlock(Block block, float temperatureMax, int startRadius,
                           float speedThermalConductivity) implements IThermalBlock {
}