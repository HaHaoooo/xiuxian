package com.haha.xiuxian.gui.Hud;

import com.haha.xiuxian.capabilities.PlayerData.Storage.DataContainer;
import com.haha.xiuxian.capabilities.PlayerData.Attach.DataInject;
import com.haha.xiuxian.XiuXian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class MainOverlay extends GuiScreen {
    private final DataContainer dataContainer;

    public MainOverlay(DataContainer dataContainer){
        this.dataContainer = dataContainer;
    }
    @SubscribeEvent
    public static void show(RenderGameOverlayEvent event) {
        if (event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            MainOverlay instance = new MainOverlay(DataInject.DataContainer);
            instance.drawGui();
        }
    }

    public void drawGui(){
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        EntityPlayer player = Minecraft.getMinecraft().player;

        float health = player.getHealth();
        float healthMax = player.getMaxHealth();
        double width = (health / healthMax) * 84;

        double lingli = dataContainer.getLingLi();
        double lingliMax = dataContainer.getLingLiMax();
        double lingliWidth = (lingli/lingliMax) * 45;

        // 绘制背景 GUI 图片
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(XiuXian.MODID, "gui/main_gui.png"));
        drawModalRectWithCustomSizedTexture(3, 5, 0, 0, 150, 48, 150, 48);

        // 设置混合模式和绘制健康值 GUI 图片
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(XiuXian.MODID, "gui/health.png"));
        drawModalRectWithCustomSizedTexture(55, 5, 0, 0, (int) width, 48, 84, 48);


        // 设置混合模式和绘制灵力值 GUI 图片
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(XiuXian.MODID, "gui/lingli.png"));
        drawModalRectWithCustomSizedTexture(72, 5, 0, 0, (int) lingliWidth, 48, 45, 48);


        // 恢复默认设置
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
    }
}
