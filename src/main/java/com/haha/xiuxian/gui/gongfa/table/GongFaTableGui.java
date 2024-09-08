package com.haha.xiuxian.gui.gongfa.table;

import com.haha.xiuxian.items.gongfa.*;
import com.haha.xiuxian.items.gongfa.gui.GongFaInfo;
import com.haha.xiuxian.util.gui.GuiUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    private static final Map<Class<? extends GongFaBase>, GongFaData> GONGFA_DATA_MAP = new HashMap<>();

    static {
        GONGFA_DATA_MAP.put(EmptyGongFaBase.class, new GongFaData("empty/", "空"));
        GONGFA_DATA_MAP.put(MetalGongFaBase.class, new GongFaData("metal/", "金"));
        GONGFA_DATA_MAP.put(WoodGongFaBase.class, new GongFaData("wood/", "木"));
        GONGFA_DATA_MAP.put(WaterGongFaBase.class, new GongFaData("water/", "水"));
        GONGFA_DATA_MAP.put(FireGongFaBase.class, new GongFaData("fire/", "火"));
        GONGFA_DATA_MAP.put(DirtGongFaBase.class, new GongFaData("dirt/", "土"));
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
        GongFaData data = null;
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

        // TODO
        if (button.id == 1) {
            mc.player.sendChatMessage("成功装备功法[" + stack.getDisplayName() + "]");
            GuiUtils.putInGongFaInventory(mc.world, stack, data.attr);
            stack.shrink(1);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(TEXTURES);
        int x = (width - WIDTH) / 2;
        int y = (height - HEIGHT) / 2;
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
    }

    private static class GongFaData {
        String prefix;
        String attr;

        GongFaData(String prefix, String attr) {
            this.prefix = prefix;
            this.attr = attr;
        }
    }
}