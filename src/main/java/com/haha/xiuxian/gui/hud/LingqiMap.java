package com.haha.xiuxian.gui.hud;

import com.haha.xiuxian.capabilities.chunk.DataAttach;
import com.haha.xiuxian.capabilities.chunk.DataContainer;
import com.haha.xiuxian.util.gui.GLHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@Deprecated
public class LingqiMap extends GuiScreen {

    public static final LingqiMap instance = new LingqiMap();
    private static final int BLOCK_SIZE = 16;
    private static final int NUM_BLOCKS = 5;
    private static final int WIDTH = BLOCK_SIZE * NUM_BLOCKS;
    private final int[][] chunkColors = new int[NUM_BLOCKS][NUM_BLOCKS];
    private final Map<Pos, Integer> blocks = new ConcurrentHashMap<>();
    private final Map<ChunkPos, int[]> cachedSurfaceColors = new ConcurrentHashMap<>();
    private final Minecraft mc = Minecraft.getMinecraft();

    public static void renderer(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            instance.render();
        }
    }

    private void render() {
        EntityPlayer player = mc.player;
        World world = mc.world;
        int mid = NUM_BLOCKS / 2;
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int startX = screenWidth - WIDTH - 10;
        int startY = 10;

        blocks.clear();
        float playerYaw = player.rotationYaw;

        int playerChunkX = (int) player.posX / 16;
        int playerChunkZ = (int) player.posZ / 16;

        IntStream.range(0, NUM_BLOCKS).parallel().forEach(i -> IntStream.range(0, NUM_BLOCKS).forEach(j -> {
            int chunkX = playerChunkX + (i - mid);
            int chunkZ = playerChunkZ + (j - mid);
            ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
            Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

            int[] surfaceColors = cachedSurfaceColors.computeIfAbsent(chunkPos, key -> {
                int[] colors = new int[16 * 16];
                extractSurfaceColors(chunk, colors);
                return colors;
            });

            assert false;
            DataContainer data = chunk.getCapability(DataAttach.LINGQI_CAP, null);
            int lingqi = (data != null) ? data.getLingQi() : 0;
            chunkColors[i][j] = getRGBFromLingQi(lingqi);

            IntStream.range(0, 16 * 16).forEach(k -> {
                int x = k % 16;
                int z = k / 16;
                int rgbColor = surfaceColors[x + z * 16];
                blocks.put(new Pos(startX + i * BLOCK_SIZE + x, startY + j * BLOCK_SIZE + z), rgbColor);
            });
        }));

        // 批量绘制方块
        GlStateManager.pushMatrix();
        blocks.forEach((pos, color) -> drawRect(pos.x, pos.z, pos.x + 1, pos.z + 1, color));
        GlStateManager.popMatrix();

        // 绘制覆盖层
        IntStream.range(0, NUM_BLOCKS).forEach(i -> IntStream.range(0, NUM_BLOCKS).forEach(j -> {
            int blockX = startX + i * BLOCK_SIZE;
            int blockY = startY + j * BLOCK_SIZE;
            GLHelper.drawTransparentRect(blockX, blockY, blockX + BLOCK_SIZE, blockY + BLOCK_SIZE, chunkColors[i][j], 100);
        }));

        drawPlayerIcon(startX, startY, playerYaw);
    }

    private int getRGBFromLingQi(int lingqi) {
        int value = Math.min(255, lingqi * 3);
        return (value << 16) | (value << 8) | value; // 直接生成 RGB 整数值
    }

    private void drawPlayerIcon(int startX, int startY, float playerYaw) {
        mc.getTextureManager().bindTexture(mc.player.getLocationSkin());
        int playerX = startX + (NUM_BLOCKS * BLOCK_SIZE) / 2;
        int playerY = startY + (NUM_BLOCKS * BLOCK_SIZE) / 2;
        int iconSize = 8;
        int translate = -iconSize / 2;

        GlStateManager.pushMatrix();
        GlStateManager.translate(playerX, playerY, 0);
        GlStateManager.rotate(playerYaw + 180, 0, 0, 1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(translate, translate, 8, 8, iconSize, iconSize, 64, 64);
        GlStateManager.popMatrix();
    }

    private void extractSurfaceColors(Chunk chunk, int[] surfaceColors) {
        IntStream.range(0, 16 * 16).parallel().forEach(i -> {
            int x = i % 16;
            int z = i / 16;
            BlockPos surfacePos = findSurfaceBlock(chunk, x, z);
            if (surfacePos != null) {
                MapColor mapColor = mc.world.getBlockState(surfacePos).getBlock().getMapColor(mc.world.getBlockState(surfacePos), mc.world, surfacePos);
                int red = (mapColor.colorValue >> 16) & 0xFF;
                int green = (mapColor.colorValue >> 8) & 0xFF;
                int blue = mapColor.colorValue & 0xFF;
                surfaceColors[x + z * 16] = new Color(red, green, blue).getRGB();
            }
        });
    }

    private BlockPos findSurfaceBlock(Chunk chunk, int x, int z) {
        for (int surfaceY = 255; surfaceY >= 0; surfaceY--) {
            BlockPos pos = new BlockPos(chunk.getPos().getXStart() + x, surfaceY, chunk.getPos().getZStart() + z);
            Block block = mc.world.getBlockState(pos).getBlock();
            if (block != Blocks.AIR) {
                return pos;
            }
        }
        return null;
    }

    static class Pos {
        final int x;
        final int z;

        Pos(int x, int z) {
            this.x = x;
            this.z = z;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pos pos = (Pos) obj;
            return x == pos.x && z == pos.z;
        }

        @Override
        public int hashCode() {
            return 31 * x + z;
        }
    }

    static class ChunkPos {
        final int x;
        final int z;

        ChunkPos(int x, int z) {
            this.x = x;
            this.z = z;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ChunkPos pos = (ChunkPos) obj;
            return x == pos.x && z == pos.z;
        }

        @Override
        public int hashCode() {
            return 31 * x + z;
        }
    }
}