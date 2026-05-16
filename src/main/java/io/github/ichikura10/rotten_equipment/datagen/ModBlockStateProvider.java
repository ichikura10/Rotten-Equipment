package io.github.ichikura10.rotten_equipment.datagen;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import io.github.ichikura10.rotten_equipment.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RottenEquipment.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerMachineBlockWithItem(ModBlocks.ROT_MACHINE);

        simpleBlockWithItem(ModBlocks.ROT_BLOCK_1.get(), cubeAll(ModBlocks.ROT_BLOCK_1.get()));
        simpleBlockWithItem(ModBlocks.ROT_BLOCK_2.get(), cubeAll(ModBlocks.ROT_BLOCK_2.get()));
        simpleBlockWithItem(ModBlocks.ROT_BLOCK_3.get(), cubeAll(ModBlocks.ROT_BLOCK_3.get()));
        simpleBlockWithItem(ModBlocks.ROT_BLOCK_4.get(), cubeAll(ModBlocks.ROT_BLOCK_4.get()));
        simpleBlockWithItem(ModBlocks.ROT_BLOCK_5.get(), cubeAll(ModBlocks.ROT_BLOCK_5.get()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void registerMachineBlockWithItem(RegistryObject<Block> block) {
        String name = block.getId().getPath();

        var modelBuilder = models().orientable(
                name,
                modLoc("block/" + name + "_side"),
                modLoc("block/" + name + "_front"),
                modLoc("block/" + name + "_side")
        );

        getVariantBuilder(block.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            return ConfiguredModel.builder()
                    .modelFile(modelBuilder)
                    .rotationY((int) facing.toYRot())
                    .build();
        });
        simpleBlockItem(block.get(), modelBuilder);
    }
}
