package com.haha.xiuxian.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.haha.xiuxian.worldgen.InitialHouse.initialHouse;

public class CommonProxy {

    public void preInit(){
        GameRegistry.registerWorldGenerator(initialHouse, 0);
    }
    public void init() {

    }

    public void postInit(){

    }
}
