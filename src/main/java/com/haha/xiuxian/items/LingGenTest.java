package com.haha.xiuxian.items;


import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class LingGenTest extends Item {

    public static Item INSTANCE = new LingGenTest();
    public LingGenTest(){
        setRegistryName("xiuxian:ling_gen_test");
        setUnlocalizedName("xiuxian.ling_gen_test");
        setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_ITEM);
        setMaxStackSize(1);
    }

    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }
}
