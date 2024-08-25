package com.haha.xiuxian.util.basic.world;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveHandler;

import java.io.File;

public class WorldUtil {
    public static String getWorldName(World world) {
        if (world.isRemote) {
            World clientWorld = Minecraft.getMinecraft().world;
            if (clientWorld == null) {
                System.err.println("Client World is null!");
                return "UnknownWorld";
            }
            return clientWorld.getWorldInfo().getWorldName();
        } else {
            ISaveHandler saveHandler = world.getSaveHandler();
            File worldDir = saveHandler.getWorldDirectory();
            return worldDir.getName();
        }
    }
}
