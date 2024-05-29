package com.haha.xiuxian.items.Gongfa;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;


@Mod.EventBusSubscriber
public class WaterGongFaBase extends Item {

    public static WaterGongFaBase INSTANCE = new WaterGongFaBase();

    public WaterGongFaBase(){
        this.setRegistryName("xiuxian:water_gongfa");
        this.setUnlocalizedName("xiuxian.water_gongfa");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_GONGFA);
        this.setMaxStackSize(1);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }
}
