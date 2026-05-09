package io.github.ichikura10.rotten_equipment.recipe;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RottenEquipment.MOD_ID);

    public static final RegistryObject<RotMachineRecipe.Serializer> ROT_MACHINE_SERIALIZER =
            SERIALIZERS.register("rot_machine", () -> RotMachineRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}