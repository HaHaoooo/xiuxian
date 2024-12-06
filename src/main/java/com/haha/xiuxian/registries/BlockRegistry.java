package com.haha.xiuxian.registries;

import com.haha.xiuxian.blocks.crops.HuaiYang;
import com.haha.xiuxian.blocks.gongfa.GongFaTable;
import com.haha.xiuxian.blocks.lingshi.LingShiOre;
import com.haha.xiuxian.blocks.zhenfa.ZhenYan;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class BlockRegistry {

    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<Item> ITEM_BLOCKS = new ArrayList<>();

    public static void init() {
        registerBlock(LingShiOre.Low.MainBlock);
        registerBlock(LingShiOre.Low.Ore1);
        registerBlock(LingShiOre.Low.Ore2);
        registerBlock(LingShiOre.Low.Ore3);
        registerBlock(LingShiOre.Low.Ore4);
        registerBlock(LingShiOre.Mid.MainBlock);
        registerBlock(LingShiOre.Mid.Ore1);
        registerBlock(LingShiOre.Mid.Ore2);
        registerBlock(LingShiOre.Mid.Ore3);
        registerBlock(LingShiOre.Mid.Ore4);
        registerBlock(LingShiOre.High.MainBlock);
        registerBlock(LingShiOre.High.Ore1);
        registerBlock(LingShiOre.High.Ore2);
        registerBlock(LingShiOre.High.Ore3);
        registerBlock(LingShiOre.High.Ore4);
        registerBlock(LingShiOre.Extreme.MainBlock);
        registerBlock(LingShiOre.Extreme.Ore1);
        registerBlock(LingShiOre.Extreme.Ore2);
        registerBlock(LingShiOre.Extreme.Ore3);
        registerBlock(LingShiOre.Extreme.Ore4);
        registerBlock(ZhenYan.INSTANCE);
        registerBlock(GongFaTable.INSTANCE);
        registerBlock(HuaiYang.INSTANCE);
    }

    // 注册方块
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
    }

    // 注册方块物品
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ITEM_BLOCKS.toArray(new Item[0]));
    }

    // 注册方块和方块物品并添加到列表
    public static void registerBlock(Block block) {
        BLOCKS.add(block);
        ITEM_BLOCKS.add(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
    }
}