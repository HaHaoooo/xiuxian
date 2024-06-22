package com.haha.xiuxian.gui.gongfashow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GongFaContainer extends Container{

    public static String fileName;
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

    @Nonnull
    @Override
    public ItemStack slotClick(int slotId, int dragType, @Nonnull ClickType clickTypeIn, @Nonnull EntityPlayer player) {
        Slot slot = inventorySlots.get(slotId);
        ItemStack item = slot.getStack();
        assert item.getTagCompound() != null;
        String attr = item.getTagCompound().getString("attribute");
        String prefix = "";
        if (attr.equals("空")){
            prefix = "empty/";
        }
        if (attr.equals("金")){
            prefix = "metal/";
        }
        if (attr.equals("木")){
            prefix = "wood/";
        }
        if (attr.equals("水")){
            prefix = "water/";
        }
        if (attr.equals("火")){
            prefix = "fire/";
        }
        if (attr.equals("土")){
            prefix = "dirt/";
        }
        fileName = prefix + Objects.requireNonNull(slot.getStack().getItem().getRegistryName()).getResourcePath();
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
