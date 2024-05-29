package com.haha.xiuxian.capabilities.PlayerData.Attach;

import com.haha.xiuxian.capabilities.PlayerData.Storage.DataContainer;
import com.haha.xiuxian.capabilities.PlayerData.Storage.DataContainerImpl;
import com.haha.xiuxian.capabilities.PlayerData.Storage.DataStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DataInject{
    public static com.haha.xiuxian.capabilities.PlayerData.Storage.DataContainer DataContainer = new DataContainerImpl();
    public static void register() {
        CapabilityManager.INSTANCE.register(DataContainer.class, new DataStorage(), () -> DataContainer);
    }
}