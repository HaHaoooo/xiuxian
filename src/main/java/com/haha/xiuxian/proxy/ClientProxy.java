package com.haha.xiuxian.proxy;

import com.haha.xiuxian.capabilities.Chunk.DataContainer;
import com.haha.xiuxian.capabilities.Chunk.DataContainerImpl;
import com.haha.xiuxian.capabilities.Chunk.DataStorage;
import com.haha.xiuxian.capabilities.PlayerData.Attach.DataHandler;
import com.haha.xiuxian.capabilities.PlayerData.Attach.DataInject;
import com.haha.xiuxian.nbt.PlayerData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

import static com.haha.xiuxian.key.KeyBindProperties.registerKey1;
import static com.haha.xiuxian.key.LingLiExShrink.registerKey2;
import static com.haha.xiuxian.key.LingqiCheck.registerKey3;


@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy{

    @Override
    public void PreInit() {
        super.PreInit();

        registerKey1();
        registerKey2();
        registerKey3();
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
