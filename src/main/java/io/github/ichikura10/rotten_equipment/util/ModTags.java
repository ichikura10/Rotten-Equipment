package io.github.ichikura10.rotten_equipment.util;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> ROT_BLOCKS = tag("rot_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RottenEquipment.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RottenEquipment.MOD_ID, name));
        }
    }
}