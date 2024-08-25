package com.haha.xiuxian.nbt;

import com.haha.xiuxian.util.basic.world.WorldUtil;
import net.minecraft.world.World;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GongFaWorldData {
    public static final String FILE_NAME = "gongfa";

    // 功法nbt记录
    public static void write(JSONObject newObject, World world) {
        Path directoryPath = Paths.get(WorldUtil.getWorldDirectory(world), "xiuxian");
        Path filePath = directoryPath.resolve(FILE_NAME + ".json");
        File directory = new File(directoryPath.toUri());
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("成功创建文件夹: " + directoryPath);
            } else {
                System.out.println("创建文件夹: " + directoryPath + "失败");
                return;
            }
        } else {
            System.out.println("文件夹已创建");
        }

        // 读取现有数据
        JSONObject existingObject = new JSONObject();
        if (Files.exists(filePath)) {
            try {
                existingObject = new JSONObject(new String(Files.readAllBytes(filePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (String key : newObject.keySet()) {
            existingObject.put(key, newObject.get(key));
        }

        // 更新
        try (BufferedWriter file = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            file.write(existingObject.toString(4));
            file.flush();
            System.out.println("已写入json: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取功法nbt
    public static JSONObject get(World world) {
        Path path = Paths.get(WorldUtil.getWorldDirectory(world), "xiuxian", FILE_NAME + ".json");
        try {
            return new JSONObject(new String(Files.readAllBytes(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
