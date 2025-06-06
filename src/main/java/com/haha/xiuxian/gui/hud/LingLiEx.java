package com.haha.xiuxian.gui.hud;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.capabilities.playerdata.DataInject;
import com.haha.xiuxian.capabilities.playerdata.IDataContainer;
import com.haha.xiuxian.key.KeyBindExGui;
import com.haha.xiuxian.nbt.XiuXianWorldData;
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
    private static final IDataContainer container = DataInject.DataContainer;

    public static int x = 5;
    public static int ybound = 27;
    public static int MetalY = ybound + 30;
    public static int WoodY = MetalY + 30;
    public static int WaterY = WoodY + 30;
    public static int FireY = WaterY + 30;
    public static int DirtY = FireY + 30;

    @SubscribeEvent
    public static void draw(RenderGameOverlayEvent event) {

        XiuXianWorldData data = new XiuXianWorldData("persistence", Minecraft.getMinecraft().world);
        if (event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.ALL && data.get().getBoolean("showLingLiEx")) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

            KeyBindExGui.adjustGui(KeyBindExGui.isShrink);

            if (Attributes.METAL.getConfigValue()) {
                double MetalWidth = (container.getMetal() / container.getMetalMax()) * 74;
                setRenderer((int) MetalWidth, MetalLocation, MetalY);
            }

            if (Attributes.WOOD.getConfigValue()) {
                double WoodWidth = (container.getWood() / container.getWoodMax()) * 74;
                setRenderer((int) WoodWidth, WoodLocation, WoodY);
            } else {
                WoodY = MetalY;
            }

            if (Attributes.WATER.getConfigValue()) {
                double WaterWidth = (container.getWater() / container.getWaterMax()) * 74;
                setRenderer((int) WaterWidth, WaterLocation, WaterY);
            } else {
                WaterY = WoodY;
            }

            if (Attributes.FIRE.getConfigValue()) {
                double FireWidth = (container.getFire() / container.getFireMax()) * 74;
                setRenderer((int) FireWidth, FireLocation, FireY);
            } else {
                FireY = WaterY;
            }

            if (Attributes.DIRT.getConfigValue()) {
                double DirtWidth = (container.getDirt() / container.getDirtMax()) * 74;
                setRenderer((int) DirtWidth, DirtLocation, DirtY);
            }


            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();
        }
    }

    private static void setRenderer(int width, ResourceLocation location, int y) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(location);
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, 100, 26, 100, 26);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(LingLiEx);
        drawModalRectWithCustomSizedTexture(x + 26, y, 0, 0, width, 26, 74, 26);
    }
}