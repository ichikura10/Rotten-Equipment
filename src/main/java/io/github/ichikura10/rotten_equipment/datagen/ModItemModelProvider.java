package io.github.ichikura10.rotten_equipment.datagen;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import io.github.ichikura10.rotten_equipment.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RottenEquipment.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ROTTEN_BOOTS);
        simpleItem(ModItems.ROTTEN_LEGGINGS);
        simpleItem(ModItems.ROTTEN_CHESTPLATE);
        simpleItem(ModItems.ROTTEN_HELMET);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RottenEquipment.MOD_ID,"item/" + item.getId().getPath()));
    }
}