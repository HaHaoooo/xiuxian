package com.haha.xiuxian.gui.gongfa;

import com.haha.xiuxian.items.gongfa.GongFaBase;
import com.haha.xiuxian.nbt.XiuXianWorldData;
import com.haha.xiuxian.nbt.infoblocks.InfoBlockCompound;
import com.haha.xiuxian.nbt.infoblocks.InfoBlockString;
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
        return true; // 如果将来要开放对玩家的访问，这里可以改为 true
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        // 假设功法槽只接受某些特定类型的物品
        return stack.getItem() instanceof GongFaBase;
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

    // 使用自定义配置json来加载功法数据
    @Mod.EventBusSubscriber
    static class NbtSerialized {

        @SubscribeEvent
        public static void loadItems(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.player.world.isRemote) return; // 只在服务端加载数据
            XiuXianWorldData worldData = new XiuXianWorldData("gongfa", event.player.world);
            InfoBlockCompound data = worldData.get();
            if (data != null) {
                loadInventoryFromJson(data);
            }
        }

        private static void loadInventoryFromJson(InfoBlockCompound data) {
            for (int i = 0; i < instance.getSizeInventory(); i++) {
                if (data.hasKey("slot_" + i)) {
                    InfoBlockCompound content = data.get("slot_" + i, InfoBlockCompound.class);
                    InfoBlockString registryNameBlock = content.get("name", InfoBlockString.class);
                    String registryName = registryNameBlock.getValue();
                    Item item = Item.getByNameOrId(registryName);
                    if (item != null) {
                        instance.setInventorySlotContents(i, new ItemStack(Objects.requireNonNull(item)));
                    }
                }
            }
        }
    }
}