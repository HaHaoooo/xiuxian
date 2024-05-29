package com.haha.xiuxian.gui.GongfaShow;

import com.haha.xiuxian.gui.Buttons.LeftButton;
import com.haha.xiuxian.gui.Buttons.RightButton;
import com.haha.xiuxian.XiuXian;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.minecraft.client.gui.inventory.GuiInventory.drawEntityOnScreen;

public class GongFaGui extends GuiContainer {
    private static final int WIDTH = 512;
    private static final int HEIGHT = 524;

    private static final ResourceLocation resourceLocation = new ResourceLocation(XiuXian.MODID, "gui/main/general.png");


    public static GongFaContainer container = GongFaContainer.INSTANCE;

    public static GongFaGui gongFaGui = new GongFaGui();

    public GongFaGui() {
        super(container);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        int guiLeft = (this.width - WIDTH) / 2 + WIDTH / 4;
        int guiTop = (this.height - HEIGHT) / 2 + HEIGHT / 4;
        this.mc.getTextureManager().bindTexture(resourceLocation);
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, WIDTH/2, HEIGHT/2, (float)WIDTH/2, (float)HEIGHT/2);

        for (Slot slot : inventorySlots.inventorySlots) {
            int offsetX = (this.width - this.xSize) / 2;
            int offsetY = (this.height - this.ySize) / 2;
            int x = slot.xPos + offsetX - 2;
            int y = slot.yPos + offsetY - 2;
            this.mc.getTextureManager().bindTexture(new ResourceLocation("xiuxian:gui/gongfa_slots.png"));
            drawModalRectWithCustomSizedTexture(x, y, 0, 0, 20, 20, 20, 20); // 绘制每个 Slot 的图标
        }

        for (Slot slot : inventorySlots.inventorySlots) {
            int offsetX = (this.width - this.xSize) / 2;
            int offsetY = (this.height - this.ySize) / 2;
            int x = slot.xPos + offsetX - 2;
            int y = slot.yPos + offsetY - 2;
            if (isPointInRegion(slot.xPos, slot.yPos, 18, 18, mouseX, mouseY)){
                List<String> hoverText = new ArrayList<>();
                hoverText.add("功法栏" + (slot.getSlotIndex() + 1) + ": ");
                if (!slot.getStack().getDisplayName().equals("空气")){
                    hoverText.add("功法名称: " + slot.getStack().getDisplayName());
                } else {
                    hoverText.add("功法名称: 暂无");
                }
                if (slot.getStack().hasTagCompound()) {
                    hoverText.add("功法属性: " + Objects.requireNonNull(slot.getStack().getTagCompound()).getString("attribute"));
                } else {
                    hoverText.add("功法属性: 暂无");
                }
                this.drawHoveringText(hoverText, x - 10, y + 40, fontRenderer);
            }
        }

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
        RightButton rightButton = new RightButton(1, x + WIDTH/2 + 10, y + HEIGHT/4 - RightButton.HEIGHT/2);
        LeftButton leftButton = new LeftButton(1, x - 35, y  + HEIGHT/4 - LeftButton.HEIGHT/2);
        this.buttonList.add(rightButton);
        this.buttonList.add(leftButton);
    }
}
