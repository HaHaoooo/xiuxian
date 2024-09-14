package com.haha.xiuxian.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class XiuXianEventPacket implements IMessage {
    public String eventType;       // 事件类型标识符
    public NBTTagCompound data;    // 事件数据

    // 默认的无参数构造函数（必须存在）
    public XiuXianEventPacket() {}

    // 带参数的构造函数，用于方便地创建包
    public XiuXianEventPacket(String eventType, NBTTagCompound data) {
        this.eventType = eventType;
        this.data = data;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.eventType = ByteBufUtils.readUTF8String(buf);
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.eventType);
        ByteBufUtils.writeTag(buf, this.data);
    }
}