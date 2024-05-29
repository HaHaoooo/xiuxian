package com.haha.xiuxian.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.haha.xiuxian.worldgen.InitialHouse.initialHouse;

public class CommonProxy {

    public void PreInit(){
        GameRegistry.registerWorldGenerator(initialHouse, 0);
    }
    public void Init() {

    }

    public void PostInit(){

    }
}
