package com.haha.xiuxian;

import com.haha.xiuxian.blocks.LingShi.Low.*;
import com.haha.xiuxian.blocks.LingShi.Mid.MidLingShiMainBlock;
import com.haha.xiuxian.blocks.LingShi.Mid.MidLingShiOre1;
import com.haha.xiuxian.blocks.ZhenFa.ZhenYan;
import com.haha.xiuxian.items.Crops.HuaiYang;
import com.haha.xiuxian.items.FaBao.FlameExplode;
import com.haha.xiuxian.items.FaBao.JumpFuLu;
import com.haha.xiuxian.items.Gongfa.*;
import com.haha.xiuxian.items.LingGenTest;
import com.haha.xiuxian.items.LingShi.ExtremeLingShi;
import com.haha.xiuxian.items.LingShi.HighLingShi;
import com.haha.xiuxian.items.LingShi.LowLingShi;
import com.haha.xiuxian.items.LingShi.MidLingShi;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
        itemRegistry.add(Gongfa.INSTANCE);
        itemRegistry.add(MetalGongFaBase.INSTANCE);
        itemRegistry.add(WoodGongFaBase.INSTANCE);
        itemRegistry.add(WaterGongFaBase.INSTANCE);
        itemRegistry.add(FireGongFaBase.INSTANCE);
        itemRegistry.add(DirtGongFaBase.INSTANCE);
        itemRegistry.add(EmptyGongFaBase.INSTANCE);
        itemRegistry.add(JumpFuLu.INSTANCE);
        itemRegistry.add(FlameExplode.INSTANCE);
        for (Item item : itemRegistry) {
            event.getRegistry().registerAll(item);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block>  event){
        blockRegsitry.add(LowLingShiMainBlock.INSTANCE);
        blockRegsitry.add(LowLingShiOre1.INSTANCE);
        blockRegsitry.add(LowLingShiOre2.INSTANCE);
        blockRegsitry.add(LowLingShiOre3.INSTANCE);
        blockRegsitry.add(LowLingShiOre4.INSTANCE);
        blockRegsitry.add(MidLingShiMainBlock.INSTANCE);
        blockRegsitry.add(MidLingShiOre1.INSTANCE);
        blockRegsitry.add(ZhenYan.INSTANCE);
        blockRegsitry.add(HuaiYang.INSTANCE);
        for (Block block : blockRegsitry){
            event.getRegistry().registerAll(block);
        }
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        itemBlockRegistry.add(new ItemBlock(LowLingShiMainBlock.INSTANCE).setRegistryName("xiuxian:low_ling_shi_main_block"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre1.INSTANCE).setRegistryName("xiuxian:low_ling_shi_ore_1"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre2.INSTANCE).setRegistryName("xiuxian:low_ling_shi_ore_2"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre3.INSTANCE).setRegistryName("xiuxian:low_ling_shi_ore_3"));
        itemBlockRegistry.add(new ItemBlock(LowLingShiOre4.INSTANCE).setRegistryName("xiuxian:low_ling_shi_ore_4"));
        itemBlockRegistry.add(new ItemBlock(MidLingShiMainBlock.INSTANCE).setRegistryName("xiuxian:mid_ling_shi_main_block"));
        itemBlockRegistry.add(new ItemBlock(MidLingShiOre1.INSTANCE).setRegistryName("xiuxian:mid_ling_shi_ore_1"));
        itemBlockRegistry.add(new ItemBlock(ZhenYan.INSTANCE).setRegistryName("xiuxian:zhen_yan"));
        itemBlockRegistry.add(new ItemBlock(HuaiYang.INSTANCE).setRegistryName("xiuxian:huai_yang_cao"));
        for (Item itemBlocks : itemBlockRegistry) {
            event.getRegistry().registerAll(itemBlocks);
        }
    }

    @Mod.EventHandler
    public static void registerLoot(FMLInitializationEvent event){
        LootTableList.register(new ResourceLocation(XiuXian.MODID, "ling_shi_pool"));
    }
}