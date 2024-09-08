package com.haha.xiuxian.gui.gongfa.table;

import com.haha.xiuxian.util.gui.GuiUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class GongFaTableContainer extends Container {

    public GongFaTableContainer(InventoryPlayer playerInventory, TileEntity tileEntity) {
        // 功法栏槽位（位于顶部中央）
        this.addSlotToContainer(new TableSlot(tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 80, 20));

        // 玩家物品栏（3行）
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlotToContainer(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 51 + row * 18));
            }
        }

        // 玩家快捷栏（1行）
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 109));  // 快捷栏位于最底部
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer playerIn, int index) {
        return GuiUtils.transferStackInSlot(this, playerIn, index, 1);
    }
}