package com.haha.xiuxian.gui.hud;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.capabilities.playerdata.storage.DataContainer;
import com.haha.xiuxian.config.MainConfig;
import com.haha.xiuxian.key.LingLiExShrink;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class LingLiEx extends Gui {
    private static final ResourceLocation MetalLocation = new ResourceLocation(XiuXian.MODID, "gui/lingli/metal_lingli.png");
    private static final ResourceLocation WoodLocation = new ResourceLocation(XiuXian.MODID, "gui/lingli/wood_lingli.png");
    private static final ResourceLocation WaterLocation = new ResourceLocation(XiuXian.MODID, "gui/lingli/water_lingli.png");
    private static final ResourceLocation FireLocation = new ResourceLocation(XiuXian.MODID, "gui/lingli/fire_lingli.png");
    private static final ResourceLocation DirtLocation = new ResourceLocation(XiuXian.MODID, "gui/lingli/dirt_lingli.png");
    private static final ResourceLocation LingLiEx = new ResourceLocation(XiuXian.MODID, "gui/lingli/lingliex.png");
    private static final DataContainer container = DataInject.DataContainer;

    public static int x = 5;
    public static int ybound = 27;
    public static int MetalY = ybound + 30;
    public static int WoodY = MetalY + 30;
    public static int WaterY = WoodY + 30;
    public static int FireY = WaterY + 30;
    public static int DirtY = FireY + 30;



    @SubscribeEvent
    public static void draw(RenderGameOverlayEvent event) {

        if (event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.ALL && container.getBooleanOfGui()) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

            if (LingLiExShrink.isShrinked){
                LingLiExShrink.expandGui();
            } else {
                LingLiExShrink.shrinkGui();
            }

            if (MainConfig.Metal) {
                double MetalWidth = (container.getMetal() / container.getMetalMax()) * 74;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(MetalLocation);
                drawModalRectWithCustomSizedTexture(x, MetalY, 0, 0, 100, 26, 100, 26);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(LingLiEx);
                drawModalRectWithCustomSizedTexture(x + 26, MetalY, 0, 0, (int) MetalWidth, 26, 74, 26);
            }

            if (MainConfig.Wood) {
                double WoodWidth = (container.getWood() / container.getWoodMax()) * 74;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(WoodLocation);
                drawModalRectWithCustomSizedTexture(x, WoodY, 0, 0, 100, 26, 100, 26);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(LingLiEx);
                drawModalRectWithCustomSizedTexture(x + 26, WoodY, 0, 0, (int) WoodWidth, 26, 74, 26);
            } else {
                WoodY = MetalY;
            }

            if (MainConfig.Water) {
                double WaterWidth = (container.getWater() / container.getWaterMax()) * 74;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(WaterLocation);
                drawModalRectWithCustomSizedTexture(x, WaterY, 0, 0, 100, 26, 100, 26);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(LingLiEx);
                drawModalRectWithCustomSizedTexture(x + 26, WaterY, 0, 0, (int) WaterWidth, 26, 74, 26);
            } else {
                WaterY = WoodY;
            }

            if (MainConfig.Fire) {
                double FireWidth = (container.getFire() / container.getFireMax()) * 74;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(FireLocation);
                drawModalRectWithCustomSizedTexture(x, FireY, 0, 0, 100, 26, 100, 26);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(LingLiEx);
                drawModalRectWithCustomSizedTexture(x + 26, FireY, 0, 0, (int) FireWidth, 26, 74, 26);
            } else {
                FireY = WaterY;
            }

            if (MainConfig.Dirt) {
                double DirtWidth = (container.getDirt() / container.getDirtMax()) * 74;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(DirtLocation);
                drawModalRectWithCustomSizedTexture(x, DirtY, 0, 0, 100, 26, 100, 26);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(LingLiEx);
                drawModalRectWithCustomSizedTexture(x + 26, DirtY, 0, 0, (int) DirtWidth, 26, 74, 26);
            }


            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();
        }
    }
}