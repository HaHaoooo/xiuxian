package com.haha.xiuxian;

import com.haha.xiuxian.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = XiuXian.MODID,
        name = XiuXian.NAME,
        version = XiuXian.VERSION,
        guiFactory = "com.haha.xiuxian.config.XiuXianConfigGuiFactory",
        dependencies = "required:patchouli"
)
public class XiuXian {
    public static final String MODID = "xiuxian";
    public static final String NAME = "XiuXian";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.haha.xiuxian.proxy.ClientProxy", serverSide = "com.haha.xiuxian.proxy.ServerProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.PreInit();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){
        proxy.Init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.PostInit();
    }
}
