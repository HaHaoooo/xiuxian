package com.haha.xiuxian;

import com.haha.xiuxian.gui.GuiHandler;
import com.haha.xiuxian.packets.*;
import com.haha.xiuxian.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.reflections.Reflections;

import java.util.Set;

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
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("xiuxian");

    @Mod.Instance
    public static XiuXian INSTANCE;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
        NETWORK.registerMessage(XiuXianEventPacketHandler.class, XiuXianEventPacket.class, 0, Side.SERVER);
        // 注册发包事件
        Reflections reflections = new Reflections("com.haha.xiuxian.events");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(XiuXianEvent.class);

        for (Class<?> clazz : annotatedClasses) {
            if (IXiuXianEventHandler.class.isAssignableFrom(clazz)) {
                try {
                    XiuXianEvent annotation = clazz.getAnnotation(XiuXianEvent.class);
                    IXiuXianEventHandler handler = (IXiuXianEventHandler) clazz.newInstance();
                    XiuXianEventRegistry.registerEventHandler(annotation.value(), handler);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        proxy.preInit();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        testJson();
        proxy.postInit();
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
