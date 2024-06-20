package com.haha.xiuxian.key;

import com.haha.xiuxian.gui.lingqi.LingQiShow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber
public class LingqiCheck {

    public static KeyBinding KEY3;

    public static void registerKey3(){
        KEY3 = new KeyBinding("key.xiuxian.key_3", KeyConflictContext.IN_GAME, Keyboard.KEY_V, "key.categories.xiuxian");
        ClientRegistry.registerKeyBinding(KEY3);
    }

    @SubscribeEvent
    public static void openGui(InputEvent.KeyInputEvent event){
        if (KEY3.isPressed()){
            if (Minecraft.getMinecraft().currentScreen == null) {
                Minecraft.getMinecraft().displayGuiScreen(LingQiShow.INSTANCE);
            }
        }
    }
}
