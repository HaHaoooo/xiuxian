package com.haha.xiuxian.gui.gongfashow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

import javax.annotation.Nonnull;

public class GongFaContainer extends Container{

    public static GongFaContainer INSTANCE = new GongFaContainer(GongFaInventory.instance);
    public GongFaContainer(IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            GongFaSlots slot = new GongFaSlots(inventory, i, i * 18, 50);
            this.addSlotToContainer(slot);
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }
}
