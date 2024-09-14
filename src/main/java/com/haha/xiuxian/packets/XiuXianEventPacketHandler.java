package com.haha.xiuxian.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class XiuXianEventPacketHandler implements IMessageHandler<XiuXianEventPacket, IMessage> {
    @Override
    public IMessage onMessage(XiuXianEventPacket message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                IXiuXianEventHandler handler = XiuXianEventRegistry.getEventHandler(message.eventType);
                if (handler != null) {
                    handler.handle(serverPlayer, message.data);
                } else {
                    System.out.println("未注册的事件类型：" + message.eventType);
                }
            });
        }
        return null;
    }
}