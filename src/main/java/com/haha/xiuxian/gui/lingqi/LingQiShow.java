package com.haha.xiuxian.gui.lingqi;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.capabilities.chunk.DataAttach;
import com.haha.xiuxian.capabilities.chunk.DataContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber
public class LingQiShow extends GuiScreen {

    public static LingQiShow INSTANCE = new LingQiShow();
    private static final int WIDTH = 248;
    private static final int HEIGHT = 166;
    private static final int BLOCK_WIDTH = 24;
    private static final int BLOCK_HEIGHT = 24;
    private static final int NUM_BLOCKS_X = 7;
    private static final int NUM_BLOCKS_Y = 5;
    private Rectangle[][] buttons;
    private int[][] buttonColors;
    private int playerX;
    private int playerY;


    @Override
    public void initGui() {
        int startX = (this.width - (NUM_BLOCKS_X * (BLOCK_WIDTH + 2))) / 2;
        int startY = (this.height - (NUM_BLOCKS_Y * (BLOCK_HEIGHT + 2))) / 2;

        this.buttons = new Rectangle[NUM_BLOCKS_X][NUM_BLOCKS_Y];
        this.buttonColors = new int[NUM_BLOCKS_X][NUM_BLOCKS_Y];
        Chunk[][] chunks = new Chunk[NUM_BLOCKS_X][NUM_BLOCKS_Y];

        for (int i = 0; i < NUM_BLOCKS_X; i++) {
            for (int j = 0; j < NUM_BLOCKS_Y; j++) {
                int blockX = startX + i * (BLOCK_WIDTH + 2);
                int blockY = startY + j * (BLOCK_HEIGHT + 2);
                this.buttons[i][j] = new Rectangle(blockX, blockY, BLOCK_WIDTH, BLOCK_HEIGHT);

                EntityPlayer player = this.mc.player;
                World world = this.mc.world;
                BlockPos pos = new BlockPos(player.posX + 16 * (i - Math.floor((double) NUM_BLOCKS_X / 2)), player.posY, player.posZ + 16 * (j - Math.floor((double) NUM_BLOCKS_Y / 2)));
                if (i - Math.floor((double) NUM_BLOCKS_X / 2) == 0 && j - Math.floor((double) NUM_BLOCKS_Y / 2) == 0) {
                    this.playerX = blockX;
                    this.playerY = blockY;
                }
                chunks[i][j] = world.getChunkFromBlockCoords(pos);
                DataContainer data = chunks[i][j].getCapability(DataAttach.LINGQI_CAP, null);
                int lingqi = 0;
                if (data != null) {
                    lingqi = data.getLingQi();
                }
                this.buttonColors[i][j] = getRGBFromLingQi(lingqi);
            }
        }
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;
        this.mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "gui/shenshi.png"));
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);


        for (int i = 0; i < NUM_BLOCKS_X; i++) {
            for (int j = 0; j < NUM_BLOCKS_Y; j++) {
                int blockX = (int) this.buttons[i][j].getX();
                int blockY = (int) this.buttons[i][j].getY();
                int blockHeight = this.buttons[i][j].height;
                int blockWidth = this.buttons[i][j].width;
                int color = this.buttonColors[i][j];
                drawRect(blockX, blockY, blockX + blockWidth, blockY + blockHeight, color);
            }
        }

        this.drawString(fontRenderer, "§lN", x + WIDTH / 2, y + 5, Color.GRAY.getRGB());
        this.drawString(fontRenderer, "§lS", x + WIDTH / 2, y + HEIGHT - 12, Color.GRAY.getRGB());
        this.drawString(fontRenderer, "§lW", x + 10, y + HEIGHT / 2 - 3, Color.GRAY.getRGB());
        this.drawString(fontRenderer, "§lE", x + WIDTH - 12, y + HEIGHT / 2 - 3, Color.GRAY.getRGB());

        this.fontRenderer.drawString("", 0, 0, Color.WHITE.getRGB());
        this.mc.getTextureManager().bindTexture(this.mc.player.getLocationSkin());
        drawModalRectWithCustomSizedTexture(this.playerX + (BLOCK_WIDTH - 16) / 2, this.playerY + (BLOCK_HEIGHT - 16) / 2, 16, 16, 16, 16, 128, 128);
    }

    private int getRGBFromLingQi(int lingqi) {
        if (lingqi == 0) {
            return Color.WHITE.getRGB();
        }
        int rgbSum = lingqi * 3;
        int b = rgbSum & 0xFF;
        int g = (rgbSum >> 8) & 0xFF;
        int r = (rgbSum >> 16) & 0xFF;

        return new Color(r, g, b).getRGB();
    }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}