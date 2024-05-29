package com.haha.xiuxian.capabilities.PlayerData.Attach;

import com.haha.xiuxian.XiuXian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class DataHandler {
    public static final ResourceLocation resourceLocation = new ResourceLocation(XiuXian.MODID, "data");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(resourceLocation, new DataProvider());
    }
}
