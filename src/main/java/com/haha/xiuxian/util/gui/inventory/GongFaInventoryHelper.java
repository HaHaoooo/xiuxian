package com.haha.xiuxian.util.gui.inventory;

import com.haha.xiuxian.nbt.XiuXianWorldData;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.json.JSONObject;

import java.util.Objects;

public class GongFaInventoryHelper {

    public static void putInInventory(World world, InventoryBasic inventory, ItemStack heldItem, String attribute) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                if (!world.isRemote) {
                    inventory.setInventorySlotContents(i, heldItem);
                }
                JSONObject data = new JSONObject();
                String itemKey = Objects.requireNonNull(heldItem.getItem().getRegistryName()).toString();

                JSONObject base = new JSONObject();
                base.put("name", itemKey);
                base.put("attribute", attribute);
                base.put("exp", 0);

                // 使用槽位编号作为 key 的一部分，确保每个槽位的数据独立
                String slotKey = "slot_" + i;
                data.put(slotKey, base);
                XiuXianWorldData worldData = new XiuXianWorldData("gongfa");
                worldData.write(data, world);

                heldItem.shrink(1);
                break;
            }
        }
    }
}
