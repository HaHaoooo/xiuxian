package com.haha.xiuxian.registries;

import com.haha.xiuxian.items.LingGenTest;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuBase;
import com.haha.xiuxian.items.gongfa.batch.*;
import com.haha.xiuxian.items.lingshi.ExtremeLingShi;
import com.haha.xiuxian.items.lingshi.HighLingShi;
import com.haha.xiuxian.items.lingshi.LowLingShi;
import com.haha.xiuxian.items.lingshi.MidLingShi;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static com.haha.xiuxian.items.fabao.fulu.base.FuLuBase.fuLuClasses;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static final List<Item> MISC = new ArrayList<>();
    public static final List<Item> GONG_FA = new ArrayList<>();
    public static final List<Item> FU_LU = new ArrayList<>();

    public static void init() {
        // 注册杂物
        MISC.add(LingGenTest.INSTANCE);
        MISC.add(LowLingShi.INSTANCE);
        MISC.add(MidLingShi.INSTANCE);
        MISC.add(HighLingShi.INSTANCE);
        MISC.add(ExtremeLingShi.INSTANCE);

        // 注册功法
        for (IGongFaEnum gongFa : EnumEmptyGongFa.values()) {
            GONG_FA.add(gongFa.getItem());
        }
        for (IGongFaEnum gongFa : EnumMetalGongFa.values()) {
            GONG_FA.add(gongFa.getItem());
        }
        for (IGongFaEnum gongFa : EnumWoodGongFa.values()) {
            GONG_FA.add(gongFa.getItem());
        }
        for (IGongFaEnum gongFa : EnumWaterGongFa.values()) {
            GONG_FA.add(gongFa.getItem());
        }
        for (IGongFaEnum gongFa : EnumFireGongFa.values()) {
            GONG_FA.add(gongFa.getItem());
        }
        for (IGongFaEnum gongFa : EnumDirtGongFa.values()) {
            GONG_FA.add(gongFa.getItem());
        }

        // 注册符箓
        for (Class<? extends FuLuBase> fuLuClass : fuLuClasses) {
            try {
                FuLuBase fuLuInstance = fuLuClass.newInstance();
                FU_LU.add(fuLuInstance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // 注册物品
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(MISC.toArray(new Item[0]));
        event.getRegistry().registerAll(GONG_FA.toArray(new Item[0]));
        event.getRegistry().registerAll(FU_LU.toArray(new Item[0]));
    }
}