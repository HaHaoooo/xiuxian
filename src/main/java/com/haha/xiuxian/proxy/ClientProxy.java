package com.haha.xiuxian.proxy;

import com.haha.xiuxian.capabilities.chunk.DataContainerImpl;
import com.haha.xiuxian.capabilities.chunk.DataStorage;
import com.haha.xiuxian.capabilities.chunk.IDataContainer;
import com.haha.xiuxian.capabilities.playerdata.DataInject;
import com.haha.xiuxian.key.KeyBindExGui;
import com.haha.xiuxian.key.KeyBindMainGui;
import com.haha.xiuxian.registries.BlockRegistry;
import com.haha.xiuxian.registries.EntityRegistry;
import com.haha.xiuxian.registries.ItemRegistry;
import com.haha.xiuxian.registries.LootRegistry;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        ItemRegistry.init();
        BlockRegistry.init();
        EntityRegistry.init();
        LootRegistry.init();
        ClientRegistry.registerKeyBinding(KeyBindMainGui.KEY1);
        ClientRegistry.registerKeyBinding(KeyBindExGui.KEY2);
        // 玩家cap注册
        DataInject.register();
        // 区块cap注册
        CapabilityManager.INSTANCE.register(IDataContainer.class, new DataStorage(), () -> DataContainerImpl.dataContainer);
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
