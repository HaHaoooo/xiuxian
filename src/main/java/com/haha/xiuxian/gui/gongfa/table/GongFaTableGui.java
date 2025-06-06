package com.haha.xiuxian.gui.gongfa.table;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.entity.tileentities.GongFaTableTileEntity;
import com.haha.xiuxian.gui.gongfa.GongFaInfo;
import com.haha.xiuxian.items.gongfa.*;
import com.haha.xiuxian.packets.XiuXianEventPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GongFaTableGui extends GuiContainer {
    private static final ResourceLocation TEXTURES = new ResourceLocation("xiuxian", "gui/gongfa/gongfa_table.png");
    private final short WIDTH = 176;
    private final short HEIGHT = 133;
    public static final Map<Class<? extends GongFaBase>, GongFaData> GONGFA_DATA_MAP = new HashMap<>();
    public static GongFaData data;

    static {
        GONGFA_DATA_MAP.put(EmptyGongFaBase.class, new GongFaData(Attributes.EMPTY.getEnglish() + "/", Attributes.EMPTY.getChinese()));
        GONGFA_DATA_MAP.put(MetalGongFaBase.class, new GongFaData(Attributes.METAL.getEnglish() + "/", Attributes.METAL.getChinese()));
        GONGFA_DATA_MAP.put(WoodGongFaBase.class, new GongFaData(Attributes.WOOD.getEnglish() + "/", Attributes.WOOD.getChinese()));
        GONGFA_DATA_MAP.put(WaterGongFaBase.class, new GongFaData(Attributes.WATER.getEnglish() + "/", Attributes.WATER.getChinese()));
        GONGFA_DATA_MAP.put(FireGongFaBase.class, new GongFaData(Attributes.FIRE.getEnglish() + "/", Attributes.FIRE.getChinese()));
        GONGFA_DATA_MAP.put(DirtGongFaBase.class, new GongFaData(Attributes.DIRT.getEnglish() + "/", Attributes.DIRT.getChinese()));
    }

    public GongFaTableGui(InventoryPlayer player, GongFaTableTileEntity tileEntity) {
        super(new GongFaTableContainer(player, tileEntity));
        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }

    @Override
    public void initGui() {
        super.initGui();
        int guiLeft = (this.width - WIDTH) / 2;
        int guiTop = (this.height - HEIGHT) / 2;

        this.buttonList.add(new GuiButtonExt(0, guiLeft + 15, guiTop + 18, 60, 20, "预览功法")); // 左侧按钮
        this.buttonList.add(new GuiButtonExt(1, guiLeft + 101, guiTop + 18, 60, 20, "装备功法")); // 右侧按钮
    }

    @Override
    protected void actionPerformed(@Nonnull GuiButton button) throws IOException {
        ItemStack stack = this.inventorySlots.inventorySlots.get(0).getStack(); // 获取物品槽中的物品栈
        if (stack.isEmpty()) {
            return;
        }
        Item gongfa = stack.getItem();
        String filename = Objects.requireNonNull(gongfa.getRegistryName()).getResourcePath() + ".json";
        World world = mc.world;
        for (Map.Entry<Class<? extends GongFaBase>, GongFaData> entry : GONGFA_DATA_MAP.entrySet()) {
            if (entry.getKey().isInstance(gongfa)) {
                data = entry.getValue();
            }
        }
        if (data == null) {
            return;
        }
        // 在客户端打开GUI
        if (button.id == 0 && world.isRemote) {
            mc.displayGuiScreen(new GongFaInfo(data.prefix + filename, 30));
        }

        // 向服务器发布自定义包
        if (button.id == 1) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("slotIndex", 0);
            XiuXianEventPacket packet = new XiuXianEventPacket("equip_gongfa", compound);
            XiuXian.NETWORK.sendToServer(packet);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(TEXTURES);
        int x = (width - WIDTH) / 2;
        int y = (height - HEIGHT) / 2;
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
    }

    public static class GongFaData {
        public String prefix;
        public String attr;

        public GongFaData(String prefix, String attr) {
            this.prefix = prefix;
            this.attr = attr;
        }
    }
}