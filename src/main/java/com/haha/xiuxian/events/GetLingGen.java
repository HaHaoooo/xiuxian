package com.haha.xiuxian.events;

import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.haha.xiuxian.config.MainConfig.*;

@Mod.EventBusSubscriber
public class GetLingGen {

    private static boolean isParticleShowing = false;
    private static int particleTimer = 0;
    private static int circleTimer = 0;

    // 判断空灵根，如果有那就把其他灵根取消
    public static boolean isEmpty() {
        if (Empty) {
            Metal = false;
            Wood = false;
            Water = false;
            Fire = false;
            Dirt = false;
            return true;
        } else {
            return false;
        }
    }

    // 生成 End Rod 粒子效果从脚底往上升
    public static void spawnEndRodParticle(EntityPlayer player) {
        double x = player.posX;
        double y = player.posY + 1.2; // 增加高度，避免粒子在地面下方
        double z = player.posZ;

        // 计算粒子的位置
        double circleOffsetX = Math.sin(Math.toRadians(circleTimer)) * 1; // 调整圆形半径大小
        double circleOffsetZ = Math.cos(Math.toRadians(circleTimer)) * 1; // 调整圆形半径大小

        double particleX = x + circleOffsetX;
        double particleZ = z + circleOffsetZ;

        // 生成 End Rod 粒子效果
        player.world.spawnParticle(EnumParticleTypes.END_ROD, particleX, y, particleZ, 0, 0, 0);
    }

    @SubscribeEvent
    public static void get(PlayerInteractEvent.RightClickItem event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack LingGenTest = new ItemStack(com.haha.xiuxian.items.LingGenTest.INSTANCE);

        if (player.getHeldItemMainhand().isItemEqual(LingGenTest)) {
            String linggen = "";
            if (isEmpty()) {
                linggen = "§f§l空灵根";
            }
            if (Metal) {
                linggen += "§6金";
            }
            if (Wood) {
                linggen += "§3木";
            }
            if (Water) {
                linggen += "§1水";
            }
            if (Fire) {
                linggen += "§c火";
            }
            if (Dirt) {
                linggen += "§e土";
            }
            if (!player.world.isRemote) {
                sendPlayerMessage(player, "您的灵根是: " + linggen);

                isParticleShowing = true;
                particleTimer = 100; // 延长3秒，每 tick 20ms，所以 60 ticks = 3 秒
                circleTimer = 0;
                DataInject.DataContainer.showGui(true);
            }
            player.getHeldItem(event.getHand()).shrink(1);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent event) {
        if (isParticleShowing && particleTimer > 0) {
            EntityPlayer player = event.player;
            spawnEndRodParticle(player);

            // 每次 tick 增加圆形粒子的位置偏移量，让粒子围绕玩家旋转一圈
            circleTimer += 5;

            // 控制粒子效果显示的时间
            particleTimer--;
        } else {
            isParticleShowing = false;
        }
    }

    public static void sendPlayerMessage(EntityPlayer player, String message) {
        // 创建一个 TextComponentString 对象，用于包装消息内容
        TextComponentString textComponent = new TextComponentString(message);

        // 调用 player.sendMessage() 方法发送消息给玩家
        player.sendMessage(textComponent);
    }
}