package com.haha.xiuxian.events;

import com.haha.xiuxian.gui.gongfa.table.GongFaTableContainer;
import com.haha.xiuxian.gui.gongfa.table.GongFaTableGui;
import com.haha.xiuxian.items.gongfa.GongFaBase;
import com.haha.xiuxian.packets.XiuXianEvent;
import com.haha.xiuxian.packets.IXiuXianEventHandler;
import com.haha.xiuxian.util.gui.GuiUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

import java.util.Map;

@XiuXianEvent("equip_gongfa")
public class EquipGongFaEvent implements IXiuXianEventHandler {
    @Override
    public void handle(EntityPlayerMP player, NBTTagCompound data) {
        int slotIndex = data.getInteger("slotIndex");
        if (player.openContainer instanceof GongFaTableContainer) {
            GongFaTableContainer container = (GongFaTableContainer) player.openContainer;
            ItemStack stack = container.getSlot(slotIndex).getStack();
            if (!stack.isEmpty()) {
                Item gongfa = stack.getItem();
                GongFaTableGui.GongFaData gongFaData = getGongFaData(gongfa);
                if (gongFaData == null) {
                    player.sendMessage(new TextComponentString("无法装备此功法"));
                    return;
                }
                GuiUtils.putInGongFaInventory(player.world, stack, gongFaData.attr);
                container.getSlot(slotIndex).decrStackSize(1);
                container.detectAndSendChanges();
                player.sendMessage(new TextComponentString("成功装备功法 [" + stack.getDisplayName() + "] "));
            }
        }
    }

    private GongFaTableGui.GongFaData getGongFaData(Item gongfa) {
        for (Map.Entry<Class<? extends GongFaBase>, GongFaTableGui.GongFaData> entry : GongFaTableGui.GONGFA_DATA_MAP.entrySet()) {
            if (entry.getKey().isInstance(gongfa)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
