package com.haha.xiuxian.gui.buttons;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.gui.gongfashow.GongFaGui;
import com.haha.xiuxian.gui.propertyshow.PropertyGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RightButton extends GuiButton {

    public static final int WIDTH = 26;
    public static final int HEIGHT = 48;

    private static final ResourceLocation resourceLocationNormal = new ResourceLocation(XiuXian.MODID, "gui/button/right/button_right.png");
    private static final ResourceLocation resourceLocationPressed = new ResourceLocation(XiuXian.MODID, "gui/button/right/button_right_pressed.png");
    private static final ResourceLocation resourceLocationHovered = new ResourceLocation(XiuXian.MODID, "gui/button/right/button_right_hover.png");
    private boolean isPressed = false;
    public RightButton(int buttonId, int x, int y) {
        super(buttonId, x, y, "");
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (isPressed) {
            mc.getTextureManager().bindTexture(resourceLocationPressed);
        } else if (this.hovered) {
            mc.getTextureManager().bindTexture(resourceLocationHovered);
        } else {
            mc.getTextureManager().bindTexture(resourceLocationNormal);
        }
        drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
    }

    @Override
    public boolean mousePressed(@Nonnull Minecraft mc, int mouseX, int mouseY) {
        if (mouseY >= this.y && mouseY <= this.y + WIDTH && mouseX >= this.x && mouseX <= this.x + HEIGHT) {
            isPressed = true;
            this.id = (this.id >= Pages.PageSum - 1) ? Pages.PageSum - 1 : this.id + 1;
            Pages.currentPage = this.id + 1;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        if (this.id == 0){
            Minecraft.getMinecraft().displayGuiScreen(PropertyGui.propertyGui);
        }
        if (this.id == 1){
            Minecraft.getMinecraft().displayGuiScreen(GongFaGui.gongFaGui);
        }
        isPressed = false;
        super.mouseReleased(mouseX, mouseY);
    }
}