package com.haha.xiuxian.util.gui.inventory;

import com.haha.xiuxian.nbt.XiuXianWorldData;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.json.JSONObject;

import java.util.Objects;

public class GongFaInventoryHelper {

    private static final String INVENTORY_TYPE = "gongfa";

    // 提取创建 JSON 数据的方法
    private static JSONObject createItemData(String itemKey, String attribute) {
        JSONObject base = new JSONObject();
        base.put("name", itemKey);
        base.put("attribute", attribute);
        base.put("exp", 0);
        return base;
    }

    public static void putInInventory(World world, InventoryBasic inventory, ItemStack heldItem, String attribute) {
        if (world.isRemote) {
            return;
        }

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                // 将物品放入空槽
                inventory.setInventorySlotContents(i, heldItem);

                // 创建 JSON 数据
                String itemKey = Objects.requireNonNull(heldItem.getItem().getRegistryName()).toString();
                JSONObject data = new JSONObject();
                data.put("slot_" + i, createItemData(itemKey, attribute));

                // 更新世界数据
                XiuXianWorldData worldData = new XiuXianWorldData(INVENTORY_TYPE);
                worldData.write(data, world);

                // 减少物品数量并退出循环
                heldItem.shrink(1);
                break;
            }
        }
    }
}