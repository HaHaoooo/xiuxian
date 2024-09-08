package com.haha.xiuxian.gui.gongfa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import javax.annotation.Nonnull;

public class GongFaSlots extends Slot {

    public GongFaSlots(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean canTakeStack(@Nonnull EntityPlayer playerIn) {
        return false;
    }
}
