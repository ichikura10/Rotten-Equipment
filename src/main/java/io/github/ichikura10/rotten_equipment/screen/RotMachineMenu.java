package io.github.ichikura10.rotten_equipment.screen;

import io.github.ichikura10.rotten_equipment.block.ModBlocks;
import io.github.ichikura10.rotten_equipment.block.entity.RotMachineBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class RotMachineMenu extends AbstractContainerMenu {
    public final RotMachineBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public RotMachineMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public RotMachineMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ROT_MACHINE_MENU.get(), pContainerId);
        checkContainerSize(inv, 2);
        blockEntity = ((RotMachineBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 53, 35));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 111, 35));
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }


    private final int ownSlotCount = 2;
    /*
     * BluSunrize
     * Copyright (c) 2023
     *
     * These codes, quickMoveStack, moveItemStackToWithMayPlace, moveItemStackToWithMayPlace, MoveItemsFunc is licensed under "Blu's License of Common Sense"
     * https://github.com/BluSunrize/ImmersiveEngineering/blob/1.20.1/LICENSE
     */
    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int slot) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slotObject = this.slots.get(slot);
        if (slotObject.hasItem()) {
            ItemStack itemstack1 = slotObject.getItem();
            itemstack = itemstack1.copy();
            if (slot < ownSlotCount) {
                if (!this.moveItemStackTo(itemstack1, ownSlotCount, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.moveItemStackToWithMayPlace(itemstack1, 0, ownSlotCount))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slotObject.set(ItemStack.EMPTY);
            else
                slotObject.setChanged();
        }

        return itemstack;
    }

    protected boolean moveItemStackToWithMayPlace(ItemStack pStack, int pStartIndex, int pEndIndex) {
        return moveItemStackToWithMayPlace(slots, this::moveItemStackTo, pStack, pStartIndex, pEndIndex);
    }

    public static boolean moveItemStackToWithMayPlace(List<Slot> slots, MoveItemsFunc move, ItemStack pStack, int pStartIndex, int pEndIndex) {
        boolean inAllowedRange = true;
        int allowedStart = pStartIndex;
        for (int i = pStartIndex; i < pEndIndex; i++) {
            boolean mayplace = slots.get(i).mayPlace(pStack);
            if (inAllowedRange && !mayplace) {
                if (move.moveItemStackTo(pStack, allowedStart, i, false))
                    return true;
                inAllowedRange = false;
            } else if (!inAllowedRange && mayplace) {
                allowedStart = i;
                inAllowedRange = true;
            }
        }
        return inAllowedRange && move.moveItemStackTo(pStack, allowedStart, pEndIndex, false);
    }

    public interface MoveItemsFunc {
        boolean moveItemStackTo(ItemStack var1, int var2, int var3, boolean var4);
    }

    /**
     * ----
     **/

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.ROT_MACHINE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
