package com.haha.xiuxian.config;

import com.haha.xiuxian.XiuXian;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Config(modid = XiuXian.MODID)
@Config.LangKey("config.xiuxian.general")
public class MainConfig {

    @Config.LangKey("config.xiuxian.general.metal")
    @Config.Comment("更改灵根为金")
    public static boolean Metal = new Random().nextBoolean();

    @Config.LangKey("config.xiuxian.general.wood")
    @Config.Comment("更改灵根为木")
    public static boolean Wood = new Random().nextBoolean();

    @Config.LangKey("config.xiuxian.general.water")
    @Config.Comment("更改灵根为水")
    public static boolean Water = new Random().nextBoolean();

    @Config.LangKey("config.xiuxian.general.fire")
    @Config.Comment("更改灵根为火")
    public static boolean Fire = new Random().nextBoolean();

    @Config.LangKey("config.xiuxian.general.dirt")
    @Config.Comment("更改灵根为土")
    public static boolean Dirt = new Random().nextBoolean();

    @Config.LangKey("config.xiuxian.general.empty")
    @Config.Comment("设置灵根为空，目前无作用")
    public static boolean Empty = false;


    @Mod.EventBusSubscriber
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(XiuXian.MODID)) {
                ConfigManager.sync(XiuXian.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
