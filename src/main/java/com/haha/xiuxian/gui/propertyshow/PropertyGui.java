package com.haha.xiuxian.gui.propertyshow;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.capabilities.playerdata.storage.DataContainer;
import com.haha.xiuxian.gui.buttons.main.LeftButton;
import com.haha.xiuxian.gui.buttons.main.Pages;
import com.haha.xiuxian.gui.buttons.main.RightButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Arrays;

import static com.haha.xiuxian.config.MainConfig.*;
import static net.minecraft.client.gui.inventory.GuiInventory.drawEntityOnScreen;

public class PropertyGui extends GuiContainer {

    public static final int WIDTH = 512;
    public static final int HEIGHT = 524;

    private static final ResourceLocation resourceLocation = new ResourceLocation(XiuXian.MODID, "gui/main/general.png");

    private static final DataContainer container = DataInject.DataContainer;

    public static final PropertyGui propertyGui = new PropertyGui(new PropertyContainer());


    public PropertyGui(Container container) {
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
        String pageShow = "页数: " + Pages.currentPage + "/" + Pages.PageSum;
        this.drawCenteredString(this.fontRenderer, pageShow, this.width / 2, guiTop - 10, Color.WHITE.getRGB());

        int health = (int) mc.player.getHealth();
        int healthMax = (int) mc.player.getMaxHealth();

        int yAlign = 100;

        int[] rowHeights = {-10, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int x = guiLeft + 40;
        int[] y = Arrays.stream(rowHeights).map(height -> guiTop + yAlign + height).toArray();
        DecimalFormat df = new DecimalFormat("#.##");
        this.fontRenderer.drawString("生命本源: " + health + "/" + healthMax, x, y[0], Color.GREEN.darker().getRGB());
        this.fontRenderer.drawString("灵力值: " + container.getLingLi() + "/" + container.getLingLiMax(), x, y[1], Color.ORANGE.darker().getRGB());
        this.fontRenderer.drawString((Metal) ? "金灵力值: " + container.getMetal() + "/" + container.getMetalMax() : "金灵力值：无", x, y[2], Color.YELLOW.darker().getRGB());
        this.fontRenderer.drawString((Wood) ? "木灵力值: " + container.getWood() + "/" + container.getWoodMax() : "木灵力值: 无", x, y[3], Color.GREEN.darker().getRGB());
        this.fontRenderer.drawString((Water) ? "水灵力值: " + container.getWater() + "/" + container.getWaterMax() : "水灵力值: 无", x, y[4], Color.CYAN.darker().getRGB());
        this.fontRenderer.drawString((Fire) ? "火灵力值: " + container.getFire() + "/" + container.getFireMax() : "火灵力值: 无", x, y[5], Color.RED.darker().getRGB());
        this.fontRenderer.drawString((Dirt) ? "土灵力值: " + container.getDirt() + "/" + container.getWoodMax() : "土灵力值: 无", x, y[6], Color.GRAY.getRGB());
        this.fontRenderer.drawString("肉身: " + df.format(this.mc.player.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue()), x, y[7], Color.white.getRGB());
        this.fontRenderer.drawString("攻伐: " + df.format(this.mc.player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()), x, y[8], Color.white.getRGB());
        this.fontRenderer.drawString("攻速: " + df.format(this.mc.player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getAttributeValue() / 4), x, y[9], Color.white.getRGB());
        this.fontRenderer.drawString("疾行: " + df.format(this.mc.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 10), x, y[10], Color.white.getRGB());
        this.fontRenderer.drawString("气运: " + df.format(this.mc.player.getEntityAttribute(SharedMonsterAttributes.LUCK).getAttributeValue()), x, y[11], Color.white.getRGB());

        int modelX = guiLeft + 190;
        int modelY = guiTop + 196;
        int modelOffsetX = -(mouseX - modelX);
        int modelOffsetY = -(mouseY - modelY + 50);
        drawEntityOnScreen(modelX, modelY, 40, modelOffsetX, modelOffsetY, this.mc.player);
        fontRenderer.drawString("", guiLeft, guiTop, Color.white.getRGB());
    }

    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - WIDTH) / 2 + WIDTH / 4;
        int y = (this.height - HEIGHT) / 2 + HEIGHT / 4;
        RightButton rightButton = new RightButton(0, x + WIDTH / 2 + 10, y + HEIGHT / 4 - RightButton.HEIGHT / 2);
        LeftButton leftButton = new LeftButton(0, x - 35, y + HEIGHT / 4 - LeftButton.HEIGHT / 2);
        this.buttonList.add(rightButton);
        this.buttonList.add(leftButton);
    }
}
