package com.haha.xiuxian.gui;

import com.haha.xiuxian.XiuXian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ToolTipHelper extends GuiScreen {
    private static boolean metal = false;
    private static boolean wood = false;
    private static boolean water = false;
    private static boolean fire = false;
    private static boolean dirt = false;
    private static boolean empty = false;
    @SubscribeEvent
    public static void ToolTipAddPics(RenderTooltipEvent.PostText event) {
        int toolTipY = event.getY();
        int toolTipX = event.getX();

        Minecraft mc = Minecraft.getMinecraft();

        if (metal && isShiftKeyDown()) {
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/metal.png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else if (!isShiftKeyDown()){
            BackUp();
        }

        if (wood && isShiftKeyDown()) {
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/wood.png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else if (!isShiftKeyDown()){
            BackUp();
        }

        if (water && isShiftKeyDown()) {
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/water.png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else if (!isShiftKeyDown()){
            BackUp();
        }

        if (fire && isShiftKeyDown()) {
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/fire.png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else if (!isShiftKeyDown()){
            BackUp();
        }

        if (dirt && isShiftKeyDown()) {
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/dirt.png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else if (!isShiftKeyDown()){
            BackUp();
        }

        if (empty && isShiftKeyDown()) {
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/empty.png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else if (!isShiftKeyDown()){
            BackUp();
        }
    }

    public static void ChangeMetal(){
        metal = true;
    }

    public static void ChangeWood(){
        wood = true;
    }
    public static void ChangeWater(){
        water = true;
    }
    public static void ChangeFire(){
        fire = true;
    }
    public static void ChangeDirt(){
        dirt = true;
    }
    public static void ChangeEmpty(){
        empty = true;
    }
    private static void BackUp(){
        metal = false;
        wood = false;
        water = false;
        fire = false;
        dirt = false;
        empty = false;
    }
}
