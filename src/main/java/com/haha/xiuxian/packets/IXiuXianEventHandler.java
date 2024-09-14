package com.haha.xiuxian.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public interface IXiuXianEventHandler {
    void handle(EntityPlayerMP player, NBTTagCompound data);
}
