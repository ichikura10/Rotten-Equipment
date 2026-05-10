package io.github.ichikura10.rotten_equipment.item;

import io.github.ichikura10.rotten_equipment.RottenEquipment;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RottenEquipment.MOD_ID);

    public static final RegistryObject<Item> ROTTEN_ITEM = ITEMS.register("rotten_item",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
