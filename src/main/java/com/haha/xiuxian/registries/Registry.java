package com.haha.xiuxian.registries;

public class Registry {

    public static void init(){
        ItemRegistry.init();
        BlockRegistry.init();
        EntityRegistry.init();
        LootRegistry.init();
    }
}