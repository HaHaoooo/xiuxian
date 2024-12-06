package com.haha.xiuxian.key;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import static com.haha.xiuxian.gui.hud.LingLiEx.*;

@Mod.EventBusSubscriber
public class KeyBindExGui {
    public static KeyBinding KEY2 = new KeyBinding("key.xiuxian.key_2", KeyConflictContext.IN_GAME, Keyboard.KEY_M, "key.categories.xiuxian");
    public static boolean isShrink = false;

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        isShrink = KEY2.isPressed();
    }

    public static void adjustGui(boolean expanding) {
        int[] elements = {WoodY, WaterY, FireY, DirtY};
        int[] base = {MetalY, WoodY, WaterY, FireY};
        boolean hasUpdates = false;

        for (int i = 0; i < elements.length; i++) {
            int targetY = base[i] + (expanding ? 30 : 4);
            if ((expanding && elements[i] < targetY) || (!expanding && elements[i] > targetY)) {
                elements[i] += expanding ? 1 : -1;
                if ((expanding && elements[i] > targetY) || (!expanding && elements[i] < targetY)) {
                    elements[i] = targetY;
                }
                hasUpdates = true;
            }
        }

        if (hasUpdates) {
            WoodY = elements[0];
            WaterY = elements[1];
            FireY = elements[2];
            DirtY = elements[3];
        }
    }
}
