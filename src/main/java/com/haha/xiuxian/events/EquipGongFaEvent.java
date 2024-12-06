package com.haha.xiuxian.events;

import com.haha.xiuxian.gui.gongfa.table.GongFaTableContainer;
import com.haha.xiuxian.gui.gongfa.table.GongFaTableGui;
import com.haha.xiuxian.packets.IXiuXianEventHandler;
import com.haha.xiuxian.packets.XiuXianEvent;
import com.haha.xiuxian.util.gui.GuiUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

@XiuXianEvent("equip_gongfa")
public class EquipGongFaEvent implements IXiuXianEventHandler {
    @Override
    public void handle(EntityPlayerMP player, NBTTagCompound data) {
        int slotIndex = data.getInteger("slotIndex");
        if (player.openContainer instanceof GongFaTableContainer) {
            GongFaTableContainer container = (GongFaTableContainer) player.openContainer;
            ItemStack stack = container.getSlot(slotIndex).getStack();
            if (!stack.isEmpty()) {
                if (GongFaTableGui.data == null) {
                    player.sendMessage(new TextComponentString("无法装备此功法"));
                    return;
                }
                GuiUtils.putInGongFaInventory(player.world, stack, GongFaTableGui.data.attr);
                container.getSlot(slotIndex).decrStackSize(1);
                container.detectAndSendChanges();
                player.sendMessage(new TextComponentString("成功装备功法 [" + stack.getDisplayName() + "] "));
            }
        }
    }
}
