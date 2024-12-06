package com.haha.xiuxian.util.gui;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GraphUtils {

    public static double[] pointX;

    public static double[] pointY;

    /**
     *
     * @author haha
     * @param alpha 0(透明) ~ 255(实心)
     */
    public static void drawTransparentRect(int left, int top, int right, int bottom, int color, int alpha) {
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        // valid range(0 ~ 255)
        alpha = Math.max(0, Math.min(255, alpha));

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha / 255.0F);

        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos(left, bottom, 0.0D).endVertex();
        buffer.pos(right, bottom, 0.0D).endVertex();
        buffer.pos(right, top, 0.0D).endVertex();
        buffer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

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

    /**
     * 由haha写的可变换角六边形
     *
     * @param centerX x
     * @param centerY y
     * @param angle1  第一个角离中心点的距离，100为上限，0为下限
     * @param angle2  与angle1相同
     * @param angle3  与angle1相同
     * @param angle4  与angle1相同
     * @param angle5  与angle1相同
     * @param angle6  与angle1相同
     * @param radius  半径
     * @param color   颜色，可用Color方法
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

        pointX = new double[6];
        pointY = new double[6];
        pointX[0] = centerX - ScaledAngle(angle1, radius) / 2;
        pointY[0] = centerY - ScaledAngle(angle1, radius);
        pointX[1] = centerX + ScaledAngle(angle2, radius) / 2;
        pointY[1] = centerY - ScaledAngle(angle2, radius);
        pointX[2] = centerX + ScaledAngle(angle3, radius) * (xAxisRadius / radius);
        pointY[2] = centerY;
        pointX[3] = centerX + ScaledAngle(angle4, radius) / 2;
        pointY[3] = centerY + ScaledAngle(angle4, radius);
        pointX[4] = centerX - ScaledAngle(angle5, radius) / 2;
        pointY[4] = centerY + ScaledAngle(angle5, radius);
        pointX[5] = centerX - ScaledAngle(angle6, radius) * (xAxisRadius / radius);
        pointY[5] = centerY;
    }

    private static double ScaledAngle(double angle, double radius) {
        return (angle / 100) * radius;
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
