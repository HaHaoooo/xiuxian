package com.haha.xiuxian.proxy;

import com.haha.xiuxian.capabilities.chunk.DataContainer;
import com.haha.xiuxian.capabilities.chunk.DataContainerImpl;
import com.haha.xiuxian.capabilities.chunk.DataStorage;
import com.haha.xiuxian.capabilities.playerdata.attach.DataHandler;
import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.nbt.PlayerData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

import static com.haha.xiuxian.key.KeyBindProperties.registerKey1;
import static com.haha.xiuxian.key.LingLiExShrink.registerKey2;


@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy{

    @Override
    public void PreInit() {
        super.PreInit();

        registerKey1();
        registerKey2();
        MinecraftForge.EVENT_BUS.register(new PlayerData());
        MinecraftForge.EVENT_BUS.register(new DataHandler());
        DataInject.register();
        CapabilityManager.INSTANCE.register(DataContainer.class, new DataStorage(), () -> DataContainerImpl.dataContainer);
        System.out.println("capability register successfully");

    }

    @Override
    public void Init() {
        super.Init();
    }

    @Override
    public void PostInit() {
        super.PostInit();
    }
}
