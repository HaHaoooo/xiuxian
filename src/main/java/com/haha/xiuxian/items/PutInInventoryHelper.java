package com.haha.xiuxian.items;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PutInInventoryHelper {

    public static void putInInventory(World world, InventoryBasic inventoryBasic, ItemStack heldItem, String attribute){
        for(int i=0; i<inventoryBasic.getSizeInventory(); i++) {
            if (inventoryBasic.getStackInSlot(i).isEmpty()) {
                if (!world.isRemote) {
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setString("attribute", attribute);
                    heldItem.setTagCompound(compound);
                    inventoryBasic.setInventorySlotContents(i, heldItem);
                }
                heldItem.shrink(1);
                break;
            }
        }
    }
}
