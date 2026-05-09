package io.github.ichikura10.rotten_equipment.compat;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import io.github.ichikura10.rotten_equipment.block.ModBlocks;
import io.github.ichikura10.rotten_equipment.recipe.RotMachineRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class RotMachineCategory implements IRecipeCategory<RotMachineRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(RottenEquipment.MOD_ID, "rot_machine");
    public static final ResourceLocation TEXTURE = new ResourceLocation(RottenEquipment.MOD_ID, "textures/gui/rot_machine.png");

    public static final RecipeType<RotMachineRecipe> ROT_MACHINE_TYPE =
            new RecipeType<>(UID, RotMachineRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public RotMachineCategory(IGuiHelper helper) {
            this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
            this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ROT_MACHINE.get()));

    }

    @Override
    public RecipeType<RotMachineRecipe> getRecipeType() {
        return ROT_MACHINE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.rotten_equipment.rot_machine");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RotMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 53, 35).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 35).addItemStack(recipe.getResultItem(null));
    }
}
