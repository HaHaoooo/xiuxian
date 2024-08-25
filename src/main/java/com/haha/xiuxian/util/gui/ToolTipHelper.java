package com.haha.xiuxian.util.gui;

import com.haha.xiuxian.XiuXian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ToolTipHelper extends GuiScreen {
    public enum TooltipType {
        METAL,
        WOOD,
        WATER,
        FIRE,
        DIRT,
        EMPTY
    }

    private static TooltipType currentTooltipType = null;

    @SubscribeEvent
    public static void ToolTipAddPics(RenderTooltipEvent.PostText event) {
        if (currentTooltipType != null && isShiftKeyDown()) {
            int toolTipY = event.getY();
            int toolTipX = event.getX();
            Minecraft mc = Minecraft.getMinecraft();
            mc.getTextureManager().bindTexture(new ResourceLocation(XiuXian.MODID, "tooltip/" + currentTooltipType.name().toLowerCase() + ".png"));
            drawModalRectWithCustomSizedTexture(toolTipX, toolTipY + 42, 0, 0, 33, 38, 33, 38);
        } else {
            ResetTooltipType();
        }
    }

    public static void ChangeTooltipType(TooltipType type) {
        currentTooltipType = type;
        ResetOtherTooltipTypes(type);
    }

    private static void ResetOtherTooltipTypes(TooltipType exclude) {
        for (TooltipType type : TooltipType.values()) {
            if (type != exclude) {
                switch (type) {
                    case METAL:
                    case WOOD:
                    case WATER:
                    case FIRE:
                    case DIRT:
                    case EMPTY:
                    default:
                        break;
                }
            }
        }
    }

    private static void ResetTooltipType() {
        currentTooltipType = null;
    }

    public static void ChangeMetal() {
        ChangeTooltipType(TooltipType.METAL);
    }

    public static void ChangeWood() {
        ChangeTooltipType(TooltipType.WOOD);
    }

    public static void ChangeWater() {
        ChangeTooltipType(TooltipType.WATER);
    }

    public static void ChangeFire() {
        ChangeTooltipType(TooltipType.FIRE);
    }

    public static void ChangeDirt() {
        ChangeTooltipType(TooltipType.DIRT);
    }

    public static void ChangeEmpty() {
        ChangeTooltipType(TooltipType.EMPTY);
    }
}
