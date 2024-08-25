package com.haha.xiuxian.util.basic.world;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;

import java.io.File;
import java.nio.file.Paths;

public class WorldUtil {
    public static String getWorldDirectory(World world) {
        // 确保这是服务器世界
        if (world instanceof WorldServer) {
            WorldServer serverWorld = (WorldServer) world;
            ISaveHandler saveHandler = serverWorld.getSaveHandler();
            File worldDir = saveHandler.getWorldDirectory();
            return worldDir.getAbsolutePath();
        } else {
            // 客户端世界或其他情况，返回客户端世界目录的合适路径
            return Paths.get(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(), "saves", world.getWorldInfo().getWorldName()).toString();
        }
    }
}
