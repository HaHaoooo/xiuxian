package com.haha.xiuxian.capabilities.playerdata.attach;

import com.haha.xiuxian.capabilities.playerdata.storage.DataContainer;
import com.haha.xiuxian.capabilities.playerdata.storage.DataContainerImpl;
import com.haha.xiuxian.capabilities.playerdata.storage.DataStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DataInject{
    public static com.haha.xiuxian.capabilities.playerdata.storage.DataContainer DataContainer = new DataContainerImpl();
    public static void register() {
        CapabilityManager.INSTANCE.register(DataContainer.class, new DataStorage(), () -> DataContainer);
    }
}