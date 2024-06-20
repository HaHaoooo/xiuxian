package com.haha.xiuxian.items.gongfa.gui;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.util.gui.Graph;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.util.ArrayList;

public class GongFaInfo extends GuiScreen {
    private final String intro;
    private final int rowPerWords;
    private final String title;
    private final int radius;
    double angle1 = 0;
    double angle2 = 0;
    double angle3 = 0;
    double angle4 = 0;
    double angle5 = 0;
    double angle6 = 0;

    double bound1;
    double bound2;
    double bound3;
    double bound4;
    double bound5;
    double bound6;

    public GongFaInfo(String intro, int rowPerWords, String title, int radius, double[] angles){
        this.intro = intro;
        this.rowPerWords = rowPerWords;
        this.title = title;
        this.radius = radius;
        this.bound1 = angles[0];
        this.bound2 = angles[1];
        this.bound3 = angles[2];
        this.bound4 = angles[3];
        this.bound5 = angles[4];
        this.bound6 = angles[5];
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(XiuXian.MODID, "gui/gongfa/gongfa_info.png"));
        int textureWidth = 732;
        int textureHeight = 400;
        int guiLeft = (this.width - textureWidth) / 2 + textureWidth / 4;
        int guiTop = (this.height - textureHeight) / 2 + textureHeight / 4;
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, textureWidth / 2, textureHeight / 2, (float) textureWidth / 2, (float) textureHeight / 2);

        int centerX = guiLeft + (textureWidth / 4) + 31;
        int centerY = guiTop + (textureHeight / 4) + 20;

        if (angle1 < bound1) {
            angle1 += 1;
        }
        if (angle2 < bound2) {
            angle2 += 1;
        }
        if (angle3 < bound3) {
            angle3 += 1;
        }
        if (angle4 < bound4) {
            angle4 += 1;
        }
        if (angle5 < bound5) {
            angle5 += 1;
        }
        if (angle6 < bound6) {
            angle6 += 1;
        }
        Graph.drawSolidHexagon(centerX, centerY, radius + 1, Color.GRAY.brighter().getRGB());

        ThickInnerHexagon(centerX, centerY, 0);
        ThickInnerHexagon(centerX, centerY, 5);
        ThickInnerHexagon(centerX, centerY, 10);
        ThickInnerHexagon(centerX, centerY, 15);
        ThickInnerHexagon(centerX, centerY, 20);
        ThickInnerHexagon(centerX, centerY, 25);

        Graph.drawPolygonalHexagon(centerX, centerY, angle1, angle2, angle3, angle4, angle5, angle6, radius, Color.GRAY.getRGB());

        ArrayList<String> list = SplitString(intro, rowPerWords);
        for (int i = 0; i < list.size(); i++) {
            fontRenderer.drawString(list.get(i), guiLeft + 90, guiTop + 20 + i * 15, Color.BLACK.getRGB(), false);
        }

        ArrayList<String> title = SplitString(this.title, 1);
        int interval = (152 - title.size() * 18) / 4;
        for (int i = 0; i < title.size(); i++) {
            fontRenderer.drawString(TextFormatting.BOLD + title.get(i), guiLeft + 41, guiTop + 64 + i * interval, Color.BLACK.getRGB(), false);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void ThickInnerHexagon(int centerX, int centerY, int dif) {
        Graph.drawHollowHexagon(centerX, centerY, radius - dif, Color.WHITE.getRGB());
        Graph.drawHollowHexagon(centerX, centerY, radius - 0.2 - dif, Color.WHITE.getRGB());
        Graph.drawHollowHexagon(centerX, centerY, radius - 0.4 - dif, Color.WHITE.getRGB());
        Graph.drawHollowHexagon(centerX, centerY, radius - 0.4 - dif, Color.WHITE.getRGB());
    }

    private ArrayList<String> SplitString(String args, int lineLength) {
        ArrayList<String> list = new ArrayList<>();
        int length = args.length();
        for (int i = 0; i < length; i += lineLength) {
            int end = Math.min(length, i + lineLength);
            list.add(args.substring(i, end));
        }
        return list;
    }
}
