package com.haha.xiuxian.gui.gongfa;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.gui.buttons.main.LeftButton;
import com.haha.xiuxian.gui.buttons.main.Pages;
import com.haha.xiuxian.gui.buttons.main.RightButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

import static net.minecraft.client.gui.inventory.GuiInventory.drawEntityOnScreen;

public class GongFaGui extends GuiContainer {
    private static final int WIDTH = 512;
    private static final int HEIGHT = 524;
    private static final ResourceLocation resourceLocation = new ResourceLocation(XiuXian.MODID, "gui/main/general.png");
    public static GongFaContainer container = GongFaContainer.instance;

    public GongFaGui() {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        int guiLeft = (this.width - WIDTH) / 2 + WIDTH / 4;
        int guiTop = (this.height - HEIGHT) / 2 + HEIGHT / 4;
        this.mc.getTextureManager().bindTexture(resourceLocation);
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, WIDTH / 2, HEIGHT / 2, (float) WIDTH / 2, (float) HEIGHT / 2);

        // 页数
        String pageShow = "页数: " + Pages.currentPage + "/" + Pages.pageSum;
        this.drawCenteredString(this.fontRenderer, pageShow, this.width / 2, guiTop - 10, Color.WHITE.getRGB());

        // 画slots的边框
        for (Slot slot : inventorySlots.inventorySlots) {
            int offsetX = (this.width - this.xSize) / 2;
            int offsetY = (this.height - this.ySize) / 2;
            int x = slot.xPos + offsetX - 2;
            int y = slot.yPos + offsetY - 2;
            this.mc.getTextureManager().bindTexture(new ResourceLocation("xiuxian:gui/gongfa/gongfa_slots.png"));
            drawModalRectWithCustomSizedTexture(x, y, 0, 0, 20, 20, 20, 20); // 绘制每个 Slot 的图标
        }

        // 绘制玩家实体
        int modelX = guiLeft + 190;
        int modelY = guiTop + 196;
        int modelOffsetX = -(mouseX - modelX);
        int modelOffsetY = -(mouseY - modelY + 50);
        drawEntityOnScreen(modelX, modelY, 40, modelOffsetX, modelOffsetY, this.mc.player);
    }

    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - WIDTH) / 2 + WIDTH / 4;
        int y = (this.height - HEIGHT) / 2 + HEIGHT / 4;
        RightButton rightButton = new RightButton(1, x + WIDTH / 2 + 10, y + HEIGHT / 4 - RightButton.HEIGHT / 2);
        LeftButton leftButton = new LeftButton(1, x - 35, y + HEIGHT / 4 - LeftButton.HEIGHT / 2);
        this.buttonList.add(rightButton);
        this.buttonList.add(leftButton);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
