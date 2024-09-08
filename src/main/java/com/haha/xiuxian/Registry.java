package com.haha.xiuxian;

import com.haha.xiuxian.blocks.gongfa.GongFaTable;
import com.haha.xiuxian.blocks.lingshi.Low.*;
import com.haha.xiuxian.blocks.lingshi.Mid.MidLingShiMainBlock;
import com.haha.xiuxian.blocks.lingshi.Mid.MidLingShiOre1;
import com.haha.xiuxian.blocks.zhenfa.ZhenYan;
import com.haha.xiuxian.entity.ZhenFaEntity1;
import com.haha.xiuxian.entity.renderer.ZhenFaEntity1Renderer;
import com.haha.xiuxian.items.LingGenTest;
import com.haha.xiuxian.items.crops.HuaiYang;
import com.haha.xiuxian.items.fabao.empty.EmptyFu1;
import com.haha.xiuxian.items.fabao.fire.FireFu1;
import com.haha.xiuxian.items.fabao.metal.MetalFu1;
import com.haha.xiuxian.items.gongfa.batch.*;
import com.haha.xiuxian.items.lingshi.ExtremeLingShi;
import com.haha.xiuxian.items.lingshi.HighLingShi;
import com.haha.xiuxian.items.lingshi.LowLingShi;
import com.haha.xiuxian.items.lingshi.MidLingShi;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.ArrayList;


@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class Registry {

    public static ArrayList<Item> itemRegistry = new ArrayList<>();

    public static ArrayList<Block> blockRegsitry = new ArrayList<>();

    public static ArrayList<Item> itemBlockRegistry = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        itemRegistry.add(LingGenTest.INSTANCE);
        itemRegistry.add(LowLingShi.INSTANCE);
        itemRegistry.add(MidLingShi.INSTANCE);
        itemRegistry.add(HighLingShi.INSTANCE);
        itemRegistry.add(ExtremeLingShi.INSTANCE);

        ////////////////////////////////////
        // 功法注册：
        // 空属性功法
        for (EnumEmptyGongFa emptyGongfa : EnumEmptyGongFa.values()){
            itemRegistry.add(emptyGongfa.getItem());
        }
        // 金属性功法
        for (EnumMetalGongFa metalGongFa : EnumMetalGongFa.values()){
            itemRegistry.add(metalGongFa.getItem());
        }
        // 木属性功法
        for (EnumWoodGongFa woodGongFa : EnumWoodGongFa.values()){
            itemRegistry.add(woodGongFa.getItem());
        }
        // 水属性功法
        for (EnumWaterGongFa waterGongFa : EnumWaterGongFa.values()){
            itemRegistry.add(waterGongFa.getItem());
        }
        // 火属性功法
        for (EnumFireGongFa fireGongFa : EnumFireGongFa.values()){
            itemRegistry.add(fireGongFa.getItem());
        }
        // 土属性功法
        for (EnumDirtGongFa dirtGongFa : EnumDirtGongFa.values()){
            itemRegistry.add(dirtGongFa.getItem());
        }
        ////////////////////////////////////

        itemRegistry.add(EmptyFu1.INSTANCE);
        itemRegistry.add(FireFu1.INSTANCE);
        itemRegistry.add(MetalFu1.INSTANCE);
        for (Item item : itemRegistry) {
            event.getRegistry().registerAll(item);
        }
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        // 空属性功法模型注册
        for (EnumEmptyGongFa emptyGongFa : EnumEmptyGongFa.values()) {
            Item gongFaItem = emptyGongFa.getItem();
            ModelLoader.setCustomModelResourceLocation(
                    gongFaItem,
                    0,
                    new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "empty_gongfa"), "inventory")
            );
        }
        // 金属性功法模型注册
        for (EnumMetalGongFa metalGongFa : EnumMetalGongFa.values()){
            Item gongFaItem = metalGongFa.getItem();
            ModelLoader.setCustomModelResourceLocation(
                    gongFaItem,
                    0,
                    new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "metal_gongfa"), "inventory")
            );
        }
        // 木属性功法模型注册
        for (EnumWoodGongFa woodGongFa : EnumWoodGongFa.values()){
            Item gongFaItem = woodGongFa.getItem();
            ModelLoader.setCustomModelResourceLocation(
                    gongFaItem,
                    0,
                    new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "wood_gongfa"), "inventory")
            );
        }
        // 水属性功法模型注册
        for (EnumWaterGongFa waterGongFa : EnumWaterGongFa.values()){
            Item gongFaItem = waterGongFa.getItem();
            ModelLoader.setCustomModelResourceLocation(
                    gongFaItem,
                    0,
                    new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "water_gongfa"), "inventory")
            );
        }
        // 火属性功法模型注册
        for (EnumFireGongFa fireGongFa : EnumFireGongFa.values()){
            Item gongFaItem = fireGongFa.getItem();
            ModelLoader.setCustomModelResourceLocation(
                    gongFaItem,
                    0,
                    new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "fire_gongfa"), "inventory")
            );
        }
        // 土属性功法模型注册
        for (EnumDirtGongFa dirtGongFa : EnumDirtGongFa.values()){
            Item gongFaItem = dirtGongFa.getItem();
            ModelLoader.setCustomModelResourceLocation(
                    gongFaItem,
                    0,
                    new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "dirt_gongfa"), "inventory")
            );
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        blockRegsitry.add(LowLingShiMainBlock.INSTANCE);
        blockRegsitry.add(LowLingShiOre1.INSTANCE);
        blockRegsitry.add(LowLingShiOre2.INSTANCE);
        blockRegsitry.add(LowLingShiOre3.INSTANCE);
        blockRegsitry.add(LowLingShiOre4.INSTANCE);
        blockRegsitry.add(MidLingShiMainBlock.INSTANCE);
        blockRegsitry.add(MidLingShiOre1.INSTANCE);
        blockRegsitry.add(ZhenYan.INSTANCE);
        blockRegsitry.add(HuaiYang.INSTANCE);
        blockRegsitry.add(GongFaTable.INSTANCE);
        for (Block block : blockRegsitry){
            event.getRegistry().registerAll(block);
        }
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        itemBlockRegistry.add(new ItemBlock(LowLingShiMainBlock.INSTANCE).setRegistryName(XiuXian.MODID,"low_ling_shi_main_block"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre1.INSTANCE).setRegistryName(XiuXian.MODID,"low_ling_shi_ore_1"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre2.INSTANCE).setRegistryName(XiuXian.MODID,"low_ling_shi_ore_2"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre3.INSTANCE).setRegistryName(XiuXian.MODID,"low_ling_shi_ore_3"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre4.INSTANCE).setRegistryName(XiuXian.MODID,"low_ling_shi_ore_4"));
        itemBlockRegistry.add(new ItemBlock(MidLingShiMainBlock.INSTANCE).setRegistryName(XiuXian.MODID,"mid_ling_shi_main_block"));
        itemBlockRegistry.add(new ItemBlock(MidLingShiOre1.INSTANCE).setRegistryName(XiuXian.MODID,"mid_ling_shi_ore_1"));
        itemBlockRegistry.add(new ItemBlock(ZhenYan.INSTANCE).setRegistryName(XiuXian.MODID,"zhen_yan"));
        itemBlockRegistry.add(new ItemBlock(HuaiYang.INSTANCE).setRegistryName(XiuXian.MODID,"huai_yang_cao"));
        itemBlockRegistry.add(new ItemBlock(GongFaTable.INSTANCE).setRegistryName(XiuXian.MODID,"gongfa_table"));
        for (Item itemBlocks : itemBlockRegistry) {
            event.getRegistry().registerAll(itemBlocks);
        }
    }

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityEntry> event){
        event.getRegistry().register(EntityEntryBuilder.create()
                        .entity(ZhenFaEntity1.class)
                        .id(new ResourceLocation(XiuXian.MODID, "zhen_fa_entity_1"), 1000)
                        .name("xiuxian.zhen_fa_entity_1")
                        .tracker(64, 10, true)
                        .build());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(ModelRegistryEvent event){
        RenderingRegistry.registerEntityRenderingHandler(ZhenFaEntity1.class, new ZhenFaEntity1Renderer.Factory());
    }

    @Mod.EventHandler
    public static void registerLoot(FMLInitializationEvent event){
        LootTableList.register(new ResourceLocation(XiuXian.MODID, "ling_shi_pool"));
    }
}