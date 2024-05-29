package com.haha.xiuxian.key;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import static com.haha.xiuxian.gui.Hud.LingLiEx.*;
@Mod.EventBusSubscriber
public class LingLiExShrink {
    public static KeyBinding KEY2;


    public static boolean isShrinked = false;

    public static void registerKey2(){
        KEY2 = new KeyBinding("key.xiuxian.key_2", KeyConflictContext.IN_GAME, Keyboard.KEY_M, "key.categories.xiuxian");
        ClientRegistry.registerKeyBinding(KEY2);
    }

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        isShrinked = KEY2.isPressed();
    }


    public static void expandGui(){
        int speed = 2;
        if (WoodY < MetalY + 30){
            WoodY += speed;
            if (WoodY >= MetalY + 30){
                WoodY = MetalY + 30;
            }
        }
        if (WaterY < WoodY + 30){
            WaterY += speed;
            if (WaterY >= WoodY + 30){
                WaterY = WoodY + 30;
            }
        }
        if (FireY < WaterY + 30){
            FireY += speed;
            if (FireY >= WaterY + 30){
                FireY = WaterY + 30;
            }
        }
        if (DirtY < FireY + 30){
            DirtY += speed;
            if (DirtY >= FireY + 30){
                DirtY = FireY + 30;
            }
        }
    }

    public static void shrinkGui(){
        int speed = 2;
        if (WoodY > MetalY){
            WoodY -= speed;
            if (WoodY <= MetalY + 4){
                WoodY = MetalY + 4;
            }
        }
        if (WaterY > WoodY){
            WaterY -= speed;
            if (WaterY <= WoodY + 4){
                WaterY = WoodY + 4;
            }
        }
        if (FireY > WaterY){
            FireY -= speed;
            if (FireY <= WaterY + 4){
                FireY = WaterY + 4;
            }
        }
        if (DirtY > FireY){
            DirtY -= speed;
            if (DirtY <= FireY + 4){
                DirtY = FireY + 4;
            }
        }
    }
}
