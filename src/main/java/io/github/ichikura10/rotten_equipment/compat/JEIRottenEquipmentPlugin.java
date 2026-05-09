package io.github.ichikura10.rotten_equipment.compat;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import io.github.ichikura10.rotten_equipment.recipe.RotMachineRecipe;
import io.github.ichikura10.rotten_equipment.screen.RotMachineScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIRottenEquipmentPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RottenEquipment.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RotMachineCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<RotMachineRecipe> rotMachineRecipes = recipeManager.getAllRecipesFor(RotMachineRecipe.Type.INSTANCE);
        registration.addRecipes(RotMachineCategory.ROT_MACHINE_TYPE, rotMachineRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RotMachineScreen.class, 73, 35, 30, 15,
                RotMachineCategory.ROT_MACHINE_TYPE);
    }
}