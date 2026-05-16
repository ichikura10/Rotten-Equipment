package io.github.ichikura10.rotten_equipment.block;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import io.github.ichikura10.rotten_equipment.block.custom.RotBlock;
import io.github.ichikura10.rotten_equipment.block.custom.RotMachineBlock;
import io.github.ichikura10.rotten_equipment.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RottenEquipment.MOD_ID);

    public static final RegistryObject<Block> ROT_MACHINE = registerBlock("rot_machine",
            () -> new RotMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ROT_BLOCK_1 = registerBlock("rot_block_1",
            () -> new RotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), 1));
    public static final RegistryObject<Block> ROT_BLOCK_2 = registerBlock("rot_block_2",
            () -> new RotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), 2));
    public static final RegistryObject<Block> ROT_BLOCK_3 = registerBlock("rot_block_3",
            () -> new RotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), 3));
    public static final RegistryObject<Block> ROT_BLOCK_4 = registerBlock("rot_block_4",
            () -> new RotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), 4));
    public static final RegistryObject<Block> ROT_BLOCK_5 = registerBlock("rot_block_5",
            () -> new RotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), 5));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
