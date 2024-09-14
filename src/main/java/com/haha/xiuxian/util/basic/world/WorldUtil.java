package com.haha.xiuxian.util.basic.world;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

public class WorldUtil {
    public static String getWorldDirectoryName(World world) {
        // 确保这是服务器世界
        if (world instanceof WorldServer) {
            WorldServer serverWorld = (WorldServer) world;
            ISaveHandler saveHandler = serverWorld.getSaveHandler();
            File worldDir = saveHandler.getWorldDirectory();
            return worldDir.getAbsolutePath();
        } else {
            // 客户端世界，使用 IntegratedServer 获取实际的文件夹名称
            String folderName = Objects.requireNonNull(Minecraft.getMinecraft().getIntegratedServer()).getFolderName();
            return Paths.get(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(), "saves", folderName).toString();
        }
    }
}
