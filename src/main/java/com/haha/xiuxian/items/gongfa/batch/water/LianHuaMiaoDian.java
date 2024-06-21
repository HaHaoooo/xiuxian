package com.haha.xiuxian.items.gongfa.batch.water;

import com.haha.xiuxian.items.gongfa.WaterGongFaBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;


@Mod.EventBusSubscriber
public class LianHuaMiaoDian extends WaterGongFaBase {

    public static LianHuaMiaoDian INHERITOR = new LianHuaMiaoDian("lian_hua_miao_dian.json", "lian_hua_miao_dian");

    public LianHuaMiaoDian(String fileName, String registryName) {
        super(fileName, registryName);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INHERITOR, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }
    
}
