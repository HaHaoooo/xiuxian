package com.haha.xiuxian.registries;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.items.gongfa.batch.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

import static com.haha.xiuxian.registries.BlockRegistry.BLOCKS;
import static com.haha.xiuxian.registries.EntityRegistry.ENTITY_RENDERERS;
import static com.haha.xiuxian.registries.ItemRegistry.FU_LU;
import static com.haha.xiuxian.registries.ItemRegistry.MISC;

@Mod.EventBusSubscriber
public class ModelRegistry {

    @SubscribeEvent
    public static void ItemModel(ModelRegistryEvent event) {
        for (Item item : MISC){
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
        }
        for (Item item : FU_LU){
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "fu_lu"), "inventory"));
        }
    }

    @SubscribeEvent
    public static void BlockModel(ModelRegistryEvent event) {
        for (Block block : BLOCKS) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(Item.getItemFromBlock(block).getRegistryName()), "inventory"));
        }
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public static void EntityModel(ModelRegistryEvent event) {
        ENTITY_RENDERERS.forEach((k, v) -> RenderingRegistry.registerEntityRenderingHandler(k, (IRenderFactory<? super Entity>) v));
    }

    @SubscribeEvent
    public static void GongFaModel(ModelRegistryEvent event) {
        for (EnumEmptyGongFa gongFa : EnumEmptyGongFa.values()) {
            ModelLoader.setCustomModelResourceLocation(gongFa.getItem(), 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "empty_gongfa"), "inventory"));
        }
        for (EnumMetalGongFa gongFa : EnumMetalGongFa.values()) {
            ModelLoader.setCustomModelResourceLocation(gongFa.getItem(), 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "metal_gongfa"), "inventory"));
        }
        for (EnumWoodGongFa gongFa : EnumWoodGongFa.values()) {
            ModelLoader.setCustomModelResourceLocation(gongFa.getItem(), 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "wood_gongfa"), "inventory"));
        }
        for (EnumWaterGongFa gongFa : EnumWaterGongFa.values()) {
            ModelLoader.setCustomModelResourceLocation(gongFa.getItem(), 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "water_gongfa"), "inventory"));
        }
        for (EnumFireGongFa gongFa : EnumFireGongFa.values()) {
            ModelLoader.setCustomModelResourceLocation(gongFa.getItem(), 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "fire_gongfa"), "inventory"));
        }
        for (EnumDirtGongFa gongFa : EnumDirtGongFa.values()) {
            ModelLoader.setCustomModelResourceLocation(gongFa.getItem(), 0, new ModelResourceLocation(new ResourceLocation(XiuXian.MODID, "dirt_gongfa"), "inventory"));
        }
    }
}
