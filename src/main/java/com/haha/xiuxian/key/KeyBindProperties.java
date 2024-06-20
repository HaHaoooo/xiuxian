package com.haha.xiuxian.key;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.gui.propertyshow.PropertyGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class KeyBindProperties {
    public static KeyBinding KEY1;

    public static void registerKey1() {
        KEY1 = new KeyBinding("key.xiuxian.key_1", KeyConflictContext.IN_GAME, Keyboard.KEY_K, "key.categories.xiuxian");
        ClientRegistry.registerKeyBinding(KEY1);
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (KEY1.isPressed()) {
            if (Minecraft.getMinecraft().currentScreen == null) {
                Minecraft.getMinecraft().displayGuiScreen(PropertyGui.propertyGui);
            }
        }
    }
}