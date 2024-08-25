package com.haha.xiuxian.worldgen;

import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public interface IStructure {
    WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
}
