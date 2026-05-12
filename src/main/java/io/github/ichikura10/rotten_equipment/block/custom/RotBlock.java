package io.github.ichikura10.rotten_equipment.block.custom;

import net.minecraft.world.level.block.Block;

public class RotBlock extends Block implements TieredBlock {
    private final int tier;

    public RotBlock(Properties pProperties, int tier) {
        super(pProperties);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
