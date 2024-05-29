package com.haha.xiuxian.gui.Hud;

import com.haha.xiuxian.XiuXian;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class CancelOriginalHealth extends Gui {

    @SubscribeEvent
    public static void hide(RenderGameOverlayEvent event) {
        if (event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            event.setCanceled(true);
        }
    }
}