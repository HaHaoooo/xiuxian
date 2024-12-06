package com.haha.xiuxian.key;

import com.haha.xiuxian.gui.propertyshow.PropertyContainer;
import com.haha.xiuxian.gui.propertyshow.PropertyGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber
public class KeyBindMainGui {
    public static KeyBinding KEY1 = new KeyBinding("key.xiuxian.key_1", KeyConflictContext.IN_GAME, Keyboard.KEY_K, "key.categories.xiuxian");

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (KEY1.isPressed()) {
            if (Minecraft.getMinecraft().currentScreen == null) {
                Minecraft.getMinecraft().displayGuiScreen(new PropertyGui(new PropertyContainer()));
            }
        }
    }
}