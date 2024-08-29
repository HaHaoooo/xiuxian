package com.haha.xiuxian.gui.gongfashow;


import com.haha.xiuxian.nbt.GongFaWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GongFaInventory extends InventoryBasic {

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
    public void openInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

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

    // 使用 Forge 的事件监听来加载功法数据
    @Mod.EventBusSubscriber
    static class NbtSerialized {

        @SubscribeEvent
        public static void loadItems(PlayerEvent.PlayerLoggedInEvent event) {
            JSONObject data = GongFaWorldData.get(event.player.world);
            if (data != null) {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject content = data.getJSONObject("slot_" + i);
                    String registryName = content.getString("name");
                    instance.setInventorySlotContents(i, new ItemStack(Objects.requireNonNull(Item.getByNameOrId(registryName))));
                }
            }
        }
    }
}