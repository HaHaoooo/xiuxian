package com.haha.xiuxian.gui.gongfashow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nonnull;

public class GongFaInventory extends InventoryBasic implements INBTSerializable<NBTTagCompound> {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(5, ItemStack.EMPTY);

    public static GongFaInventory instance = new GongFaInventory("gongfa", false, 5);

    public GongFaInventory(String title, boolean customName, int slotCount) {
        super(title, customName, slotCount);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("功法");
    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory.get(index);
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = getStackInSlot(index);
        if (!itemstack.isEmpty()) {
            if (itemstack.getCount() <= count) {
                setInventorySlotContents(index, ItemStack.EMPTY);
            } else {
                itemstack = itemstack.splitStack(count);
                markDirty();
            }
        }
        return itemstack;
    }

    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
        inventory.set(index, stack);
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }


    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {}

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Nonnull
    @Override
    public String getName() {
        return "功法";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    //Nbt部分，用于储存功法

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (!stack.isEmpty()) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                stack.writeToNBT(itemTag);
                list.appendTag(itemTag);
            }
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("items", list);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        NBTTagList list = compound.getTagList("items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound itemTag = list.getCompoundTagAt(i);
            int slot = itemTag.getByte("Slot") & 255;
            if (slot < getSizeInventory()) {
                setInventorySlotContents(slot, new ItemStack(itemTag));
            }
        }
    }


    @Mod.EventBusSubscriber
    static class NbtSerialized {

        @SubscribeEvent
        public static void loadItems(PlayerEvent.PlayerLoggedInEvent event){
            instance.deserializeNBT(event.player.getEntityData().getCompoundTag("gongfaNbt"));
        }

        @SubscribeEvent
        public static void saveItems(PlayerEvent.PlayerLoggedOutEvent event){
            event.player.getEntityData().setTag("gongfaNbt", instance.serializeNBT());
        }
    }
}