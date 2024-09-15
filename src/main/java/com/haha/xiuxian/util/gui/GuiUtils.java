package com.haha.xiuxian.util.gui;

import com.haha.xiuxian.gui.gongfa.GongFaInventory;
import com.haha.xiuxian.nbt.XiuXianWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GuiUtils {

    /**
     * 通用的 transferStackInSlot 方法，用于将物品从一个容器转移到另一个容器。
     *
     * @param container 当前的容器实例
     * @param player 玩家实例
     * @param index 被点击槽的索引
     * @param numSlots 目标容器的物品槽数量（一般是TileEntity或自定义GUI的槽）
     * @return 转移后的物品栈，如果失败则返回ItemStack.EMPTY
     */
    @Nonnull
    public static ItemStack transferStackInSlot(Container container, EntityPlayer player, int index, int numSlots) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = container.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < numSlots) {
                // 从TileEntity的物品槽转移到玩家的物品栏和快捷栏
                if (mergeItemStack(container, itemstack1, numSlots, container.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // 从玩家物品栏或快捷栏转移到TileEntity的物品槽
                if (mergeItemStack(container, itemstack1, 0, numSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    /**
     * 处理物品槽之间的物品转移，类似于Container中的mergeItemStack逻辑。
     *
     * @param container 当前的容器
     * @param stack 要转移的物品栈
     * @param startIndex 开始转移的槽位索引
     * @param endIndex 结束转移的槽位索引
     * @param reverseDirection 是否反向转移
     * @return 如果转移成功返回true，否则返回false
     */
    public static boolean mergeItemStack(Container container, ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;

        if (reverseDirection) {
            i = endIndex - 1;
        }

        if (stack.isStackable()) {
            while (!stack.isEmpty()) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else {
                    if (i >= endIndex) {
                        break;
                    }
                }

                Slot slot = container.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();

                if (!itemstack.isEmpty() && ItemStack.areItemsEqual(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());

                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.onSlotChanged();
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.onSlotChanged();
                        flag = true;
                    }
                }

                if (reverseDirection) {
                    i--;
                } else {
                    i++;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }

            while (true) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else {
                    if (i >= endIndex) {
                        break;
                    }
                }

                Slot slot = container.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();

                if (itemstack.isEmpty() && slot.isItemValid(stack)) {
                    if (stack.getCount() > slot.getSlotStackLimit()) {
                        slot.putStack(stack.splitStack(slot.getSlotStackLimit()));
                    } else {
                        slot.putStack(stack.copy());
                        stack.setCount(0);
                    }

                    slot.onSlotChanged();
                    flag = true;
                    break;
                }

                if (reverseDirection) {
                    i--;
                } else {
                    i++;
                }
            }
        }

        return !flag;
    }

    public static void putInGongFaInventory(World world, ItemStack gongfa, String attribute) {
        GongFaInventory inventory = GongFaInventory.instance;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                inventory.setInventorySlotContents(i, gongfa.copy());
                String itemKey = Objects.requireNonNull(gongfa.getItem().getRegistryName()).toString();
                NBTTagCompound data = new NBTTagCompound();
                NBTTagCompound base = new NBTTagCompound();
                base.setString("name", itemKey);
                base.setString("attribute", attribute);
                base.setInteger("exp", 0);
                data.setTag("slot_" + i, base);
                XiuXianWorldData worldData = new XiuXianWorldData("gongfa", world);
                worldData.write(data);
                inventory.markDirty();
                break;
            }
        }
    }
}