package com.haha.xiuxian.registries;

import com.haha.xiuxian.XiuXian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.ArrayList;
import java.util.List;

public class LootRegistry {

    private static final List<ResourceLocation> LOOT_TABLE = new ArrayList<>();

    public static void init(){
        LOOT_TABLE.add(new ResourceLocation(XiuXian.MODID, "ling_shi_pool"));
    }

    @Mod.EventHandler
    public static void registerLoot(FMLInitializationEvent event) {
        for (ResourceLocation resourceLocation : LOOT_TABLE) {
            LootTableList.register(resourceLocation);
        }
    }
}
