package com.haha.xiuxian.gui.gongfashow;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.gui.buttons.LeftButton;
import com.haha.xiuxian.gui.buttons.Pages;
import com.haha.xiuxian.gui.buttons.RightButton;
import com.haha.xiuxian.items.gongfa.gui.GongFaInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.minecraft.client.gui.inventory.GuiInventory.drawEntityOnScreen;

public class GongFaGui extends GuiContainer {
    private static final int WIDTH = 512;
    private static final int HEIGHT = 524;
    private static final ResourceLocation resourceLocation = new ResourceLocation(XiuXian.MODID, "gui/main/gongfa.png");
    public static GongFaContainer container = GongFaContainer.INSTANCE;
    public static GongFaGui gongFaGui = new GongFaGui();

    private String name = "功法名字";
    private String rank = "功法品级";
    private String levels = "当前功法层数";
    private double speed = 0;
    private double strength = 0;
    private double health = 0;
    private double defence = 0;
    private double comprehension = 0;
    private double spirit = 0;


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
        String pageShow = "页数: " + Pages.currentPage + "/" + Pages.PageSum;
        this.drawCenteredString(this.fontRenderer, pageShow, this.width / 2, guiTop - 10, Color.WHITE.getRGB());

        for (Slot slot : inventorySlots.inventorySlots) {
            if (slot.yPos > 30) {
                slot.yPos -= 1;
            }
        }

        // 功法信息打印
        String fileName = GongFaContainer.fileName + ".json";
        Path path = Paths.get(System.getProperty("user.dir").replace("run", "src"), "main", "resources", "assets", "gongfa", fileName);
        try {
            String content = new String(Files.readAllBytes(path));
            JSONObject contentObject = new JSONObject(content);
            JSONObject properties = contentObject.getJSONObject("properties");
            JSONObject levels = contentObject.getJSONObject("levels");
            this.name = contentObject.getString("name");
            this.speed = properties.getDouble("speed");
            this.strength = properties.getDouble("strength");
            this.health = properties.getDouble("health");
            this.defence = properties.getDouble("defence");
            this.comprehension = properties.getDouble("comprehension");
            this.spirit = properties.getDouble("spirit");
            double propertiesSum = speed + strength + health + defence + comprehension + spirit;
            this.rank = GongFaInfo.calRank(contentObject.getString("rank"), propertiesSum);
            this.levels = levels.getJSONArray("name").getString(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fontRenderer != null) {
            int centerX = guiLeft + WIDTH / 8 - 6;
            int centerY = guiTop + HEIGHT / 8 + 63;
            fontRenderer.drawString(name, centerX, centerY, Color.WHITE.getRGB(), false);
            fontRenderer.drawString("速度: " + speed, centerX - 5, centerY + 10, Color.BLACK.getRGB(), false);
            fontRenderer.drawString("力量: " + strength, centerX - 5, centerY + 20, Color.BLACK.getRGB(), false);
            fontRenderer.drawString("生命: " + health, centerX - 5, centerY + 30, Color.BLACK.getRGB(), false);
            fontRenderer.drawString("防御: " + defence, centerX - 5, centerY + 40, Color.BLACK.getRGB(), false);
            fontRenderer.drawString("悟性: " + comprehension, centerX - 5, centerY + 50, Color.BLACK.getRGB(), false);
            fontRenderer.drawString("神识: " + spirit, centerX - 5, centerY + 60, Color.BLACK.getRGB(), false);
            fontRenderer.drawString(rank, centerX - 5, centerY + 75, Color.BLACK.getRGB(), false);
            fontRenderer.drawString(levels, centerX - 5, centerY + 85, Color.BLACK.getRGB(), false);
            fontRenderer.drawString("", guiLeft, guiTop, Color.WHITE.getRGB());
        }

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
