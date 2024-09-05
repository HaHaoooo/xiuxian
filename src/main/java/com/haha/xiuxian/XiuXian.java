package com.haha.xiuxian;

import com.haha.xiuxian.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

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

    private static final Logger logger = LogManager.getLogger(XiuXian.class.getName());


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
        testJson();
        proxy.PostInit();
    }

    public void testJson() {
        logger.info("This is a log message.");
        try {
            JSONObject obj = new JSONObject();
            obj.put("key", "value");
            logger.info("JSON created successfully: " + obj);
        } catch (JSONException e) {
            logger.error("Failed to create JSON", e);
        }
    }
}
