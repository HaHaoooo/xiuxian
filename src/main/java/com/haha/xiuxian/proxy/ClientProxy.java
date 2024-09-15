package com.haha.xiuxian.proxy;

import com.haha.xiuxian.capabilities.chunk.DataContainer;
import com.haha.xiuxian.capabilities.chunk.DataContainerImpl;
import com.haha.xiuxian.capabilities.chunk.DataStorage;
import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

import static com.haha.xiuxian.key.KeyBindProperties.KEY1;
import static com.haha.xiuxian.key.LingLiExShrink.KEY2;


@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        ClientRegistry.registerKeyBinding(KEY1);
        ClientRegistry.registerKeyBinding(KEY2);
        DataInject.register();
        CapabilityManager.INSTANCE.register(DataContainer.class, new DataStorage(), () -> DataContainerImpl.dataContainer);
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
