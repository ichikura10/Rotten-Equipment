package io.github.ichikura10.rotten_equipment.item;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RottenEquipment.MOD_ID);

    public static final RegistryObject<Item> ROTTEN_ITEM = ITEMS.register("rotten_item",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROTTEN_HELMET = ITEMS.register("rotten_helmet",
            () -> new ArmorItem(ModArmorMaterials.ROT_ITEM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_CHESTPLATE = ITEMS.register("rotten_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ROT_ITEM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_LEGGINGS = ITEMS.register("rotten_leggings",
            () -> new ArmorItem(ModArmorMaterials.ROT_ITEM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_BOOTS = ITEMS.register("rotten_boots",
            () -> new ArmorItem(ModArmorMaterials.ROT_ITEM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
