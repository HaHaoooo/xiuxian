package com.haha.xiuxian.util.gui;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GraphHelper {

    public static void drawSolidHexagon(double centerX, double centerY, double radius, int color) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        float alpha = (float) (color >> 24 & 255) / 255.0F;

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(r, g, b, alpha);
        bufferbuilder.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);

        HexagonShapeFormat(centerX, centerY, radius, tessellator, bufferbuilder);

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawHollowHexagon(double centerX, double centerY, double radius, int color) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        float alpha = (float) (color >> 24 & 255) / 255.0F;

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(r, g, b, alpha);
        bufferbuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);

        HexagonShapeFormat(centerX, centerY, radius, tessellator, bufferbuilder);

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /** 由haha写的可变换角六边形
     * @param centerX x坐标系
     * @param centerY y坐标系
     * @param angle1 第一个角离中心点的距离，100为上限，0为下限
     * @param angle2 与angle1相同
     * @param angle3 与angle1相同
     * @param angle4 与angle1相同
     * @param angle5 与angle1相同
     * @param angle6 与angle1相同
     * @param radius 六边形半径
     * @param color 颜色，可用Color方法
     */
    public static void drawPolygonalHexagon(double centerX, double centerY, double angle1, double angle2, double angle3, double angle4, double angle5, double angle6, double radius, int color) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        float alpha = 1.5F;

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(r, g, b, alpha);
        bufferbuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        double xAxisRadius = Math.sqrt((radius * radius) + (radius / 2 * radius / 2));

        bufferbuilder.pos(centerX - ScaledAngle(angle1, radius) / 2, centerY - ScaledAngle(angle1, radius), 0).endVertex();
        bufferbuilder.pos(centerX + ScaledAngle(angle2, radius) / 2, centerY - ScaledAngle(angle2, radius), 0).endVertex();
        bufferbuilder.pos(centerX + ScaledAngle(angle3, radius) * (xAxisRadius / radius), centerY, 0).endVertex();
        bufferbuilder.pos(centerX + ScaledAngle(angle4, radius) / 2, centerY + ScaledAngle(angle4, radius), 0).endVertex();
        bufferbuilder.pos(centerX - ScaledAngle(angle5, radius) / 2, centerY + ScaledAngle(angle5, radius), 0).endVertex();
        bufferbuilder.pos(centerX - ScaledAngle(angle6, radius) * (xAxisRadius / radius), centerY, 0).endVertex();

        bufferbuilder.pos(centerX - ScaledAngle(angle1, radius) / 2, centerY - ScaledAngle(angle1, radius), 0).endVertex();

        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private static double ScaledAngle(double angle, double radius){
        return (angle/100)*radius;
    }

    private static void HexagonShapeFormat(double centerX, double centerY, double radius, Tessellator tessellator, BufferBuilder bufferbuilder) {

        double xAxisLength = Math.sqrt((radius * radius) + (radius / 2 * radius / 2));
        bufferbuilder.pos(centerX - radius / 2, centerY + radius, 0).endVertex();
        bufferbuilder.pos(centerX + radius / 2, centerY + radius, 0).endVertex();
        bufferbuilder.pos(centerX + xAxisLength, centerY, 0).endVertex();

        bufferbuilder.pos(centerX + radius / 2, centerY - radius, 0).endVertex();
        bufferbuilder.pos(centerX - radius / 2, centerY - radius, 0).endVertex();
        bufferbuilder.pos(centerX - xAxisLength, centerY, 0).endVertex();

        bufferbuilder.pos(centerX - radius / 2, centerY + radius, 0).endVertex();
        tessellator.draw();
    }
}
