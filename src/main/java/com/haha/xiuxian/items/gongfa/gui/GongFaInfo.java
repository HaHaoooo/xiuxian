package com.haha.xiuxian.items.gongfa.gui;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.util.files.FileHelper;
import com.haha.xiuxian.util.gui.GraphHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class GongFaInfo extends GuiScreen {
    private final String title;
    private final JSONArray description;
    private final JSONObject levels;
    private final String rank;
    private final int radius;
    double[] angles = new double[]{0, 0, 0, 0, 0, 0};
    double[] bounds = new double[6];
    private final String element;
    private final Item item;

    public GongFaInfo(String fileName, int radius) throws IOException {
        String content = FileHelper.getResourceLocation("gongfa/" + fileName);
        JSONObject contentObject = new JSONObject(content);
        String name = contentObject.getString("name");
        JSONArray description = contentObject.getJSONArray("description");
        JSONObject properties = contentObject.getJSONObject("properties");
        JSONObject levels = contentObject.getJSONObject("levels");
        String rank = contentObject.getString("rank");

        this.title = name;
        this.description = description;
        this.levels = levels;
        this.rank = rank;
        this.radius = radius;
        this.bounds[0] = properties.getDouble("speed");
        this.bounds[1] = properties.getDouble("strength");
        this.bounds[2] = properties.getDouble("health");
        this.bounds[3] = properties.getDouble("defence");
        this.bounds[4] = properties.getDouble("comprehension");
        this.bounds[5] = properties.getDouble("spirit");
        this.element = fileName.split("/")[0];
        this.item = Item.getByNameOrId("xiuxian:" + fileName.split("/")[1].replace(".json", ""));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (fontRenderer != null) {
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(XiuXian.MODID, "gui/gongfa/gongfa_info.png"));
            int textureWidth = 732;
            int textureHeight = 400;
            int guiLeft = (this.width - textureWidth) / 2 + textureWidth / 4;
            int guiTop = (this.height - textureHeight) / 2 + textureHeight / 4;
            drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, textureWidth / 2, textureHeight / 2, (float) textureWidth / 2, (float) textureHeight / 2);

            int centerX = guiLeft + (textureWidth / 4) + 31;
            int centerY = guiTop + (textureHeight / 4) + 20;

            // 绘制雷达图
            updateAngles();
            GraphHelper.drawSolidHexagon(centerX, centerY, radius + 1, Color.GRAY.brighter().getRGB());
            for (int i = 0; i < 6; i++) {
                ThickInnerHexagon(centerX, centerY, i * 5);
            }
            GraphHelper.drawPolygonalHexagon(centerX, centerY, angles[0], angles[1], angles[2], angles[3], angles[4], angles[5], radius, Color.GRAY.getRGB());

            String[] prefix = new String[]{"速度", "力量", "生命", "防御", "悟性", "神识"};
            for (int i = 0; i < 6; i++) {
                double x = GraphHelper.pointX[i];
                double y = GraphHelper.pointY[i];
                if (mouseX >= x - 2 && mouseX <= x + 2 && mouseY >= y - 2 && mouseY <= y + 2) {
                    this.drawHoveringText(prefix[i] + ": " + bounds[i] + "/100", (int) x, (int) y);
                    this.drawGradientRect((int) (x - 1), (int) (y - 1), (int) (x + 1), (int) (y + 1), Color.lightGray.getRGB(), Color.DARK_GRAY.getRGB());
                    GlStateManager.disableRescaleNormal();
                    RenderHelper.disableStandardItemLighting();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                }
            }

            // 重置界面颜色
            fontRenderer.drawString("", guiLeft, guiTop, Color.WHITE.getRGB());

            // 绘制元素图
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(XiuXian.MODID, "textures/misc/" + element + ".png"));
            int elementWidth = 40;
            int elementHeight = 40;
            int elementX = centerX - 20;
            int elementY = centerY - 100;
            drawModalRectWithCustomSizedTexture(elementX, elementY, 0, 0, elementWidth, elementHeight, elementWidth, elementHeight);

            // 功法文字类信息
            // 功法名字
            char[] title = this.title.toCharArray();
            int interval = (152 - title.length * 18) / 4;
            for (int i = 0; i < title.length; i++) {
                fontRenderer.drawString(TextFormatting.BOLD + String.valueOf(title[i]), guiLeft + 41, guiTop + 64 + i * interval, Color.BLACK.getRGB(), false);
            }

            // 功法描述
            for (int i = 0; i < description.length(); i++) {
                fontRenderer.drawString(description.getString(i), guiLeft + 90, guiTop + 20 + i * 15, Color.BLACK.getRGB(), false);
            }

            // 功法层数以及其描述
            JSONArray levelName = levels.getJSONArray("name");
            ItemStack gongfa = new ItemStack(item);
            String lockOrUnlock;
            int levelUnlocked = 0;
            NBTTagCompound tag = gongfa.getTagCompound();
            if (tag != null) levelUnlocked = tag.getByte("levelUnlocked");

            for (int i = 0; i < levelName.length(); i++) {
                String key = levelName.getString(i);
                int space = 10;
                int x = (i % 2 == 0) ? guiLeft + 295 - space : guiLeft + 295 + space;
                int y = guiTop + 50 + i * 25;
                lockOrUnlock = (i < levelUnlocked) ? "unlocked" : "locked";
                // 重置界面颜色
                fontRenderer.drawString("", guiLeft, guiTop, Color.WHITE.getRGB());
                // 绘制图标
                ResourceLocation icon = new ResourceLocation(XiuXian.MODID, "textures/misc/" + lockOrUnlock + ".png");
                Minecraft.getMinecraft().renderEngine.bindTexture(icon);
                drawModalRectWithCustomSizedTexture(x - 10, y, 0, 0, 8, 8, 8, 8);

                fontRenderer.drawString(key, x, y, Color.BLACK.getRGB(), false);
            }

            // 功法品级
            double angleSum = Arrays.stream(angles).sum();
            String rankShow = calRank(rank, angleSum);
            int rankColor;
            if (angleSum <= 150) {
                rankColor = Color.YELLOW.darker().getRGB();
            } else if (angleSum <= 300) {
                rankColor = Color.ORANGE.darker().getRGB();
            } else if (angleSum <= 500) {
                rankColor = Color.RED.darker().getRGB();
            } else {
                rankColor = Color.PINK.darker().getRGB();
            }
            fontRenderer.drawSplitString("§l" + rankShow, elementX - 15, elementY + 5, 15, rankColor);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void ThickInnerHexagon(int centerX, int centerY, int dif) {
        GraphHelper.drawHollowHexagon(centerX, centerY, radius - dif, Color.WHITE.getRGB());
        GraphHelper.drawHollowHexagon(centerX, centerY, radius - 0.2 - dif, Color.WHITE.getRGB());
        GraphHelper.drawHollowHexagon(centerX, centerY, radius - 0.4 - dif, Color.WHITE.getRGB());
        GraphHelper.drawHollowHexagon(centerX, centerY, radius - 0.4 - dif, Color.WHITE.getRGB());
    }

    private void updateAngles() {
        for (int i = 0; i < 6; i++) {
            if (angles[i] < bounds[i]) {
                angles[i] += 1;
            }
        }
    }

    public static String calRank(String rank, double propertiesSum) {
        String rankShow;
        if (propertiesSum <= 150) {
            rankShow = rank + "下品";
        } else if (propertiesSum <= 300) {
            rankShow = rank + "中品";
        } else if (propertiesSum <= 500) {
            rankShow = rank + "上品";
        } else {
            rankShow = rank + "极品";
        }
        return rankShow;
    }
}
