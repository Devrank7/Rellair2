package org.rellair2.com.api.block;

import net.minecraft.world.level.block.Block;

import java.util.List;

public interface IThermalBlock {
    float temperatureMax();
    int startRadius();
    float speedThermalConductivity();
    Block block();

}
