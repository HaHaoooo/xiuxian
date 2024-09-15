package com.haha.xiuxian.nbt;

import com.haha.xiuxian.util.basic.NBTJsonConverter;
import com.haha.xiuxian.util.world.WorldUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XiuXianWorldData {

    private final Path directoryPath;
    private final Path filePath;

    public XiuXianWorldData(String name, World world) {
        this.directoryPath = Paths.get(WorldUtil.getWorldDirectoryName(world), "xiuxian");
        this.filePath = directoryPath.resolve(name + ".json");
    }

    // 写入 NBTTagCompound 数据到文件
    public void write(NBTTagCompound newData) {
        try {
            Files.createDirectories(directoryPath);
        } catch (IOException e) {
            System.err.println("无法创建文件夹: " + directoryPath);
            e.printStackTrace();
            return;
        }

        // 读取现有数据
        JSONObject existingData = new JSONObject();
        if (Files.exists(filePath)) {
            try {
                String fileContent = new String(Files.readAllBytes(filePath));
                existingData = new JSONObject(fileContent);
            } catch (IOException e) {
                System.err.println("读取现有文件失败: " + filePath);
                e.printStackTrace();
            }
        }

        // 将 NBTTagCompound 转换为 JSON
        JSONObject newDataJson = NBTJsonConverter.nbtToJson(newData);

        // 合并新数据到现有数据
        for (String key : newDataJson.keySet()) {
            existingData.put(key, newDataJson.get(key));
        }

        // 写入文件
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            fileWriter.write(existingData.toString(4));  // 格式化 JSON 输出
            fileWriter.flush();
            System.out.println("已成功写入文件: " + filePath);
        } catch (IOException e) {
            System.err.println("写入文件失败: " + filePath);
            e.printStackTrace();
        }
    }

    // 读取 NBTTagCompound 数据
    public NBTTagCompound get() {
        if (!Files.exists(filePath)) {
            System.err.println("文件不存在: " + filePath);
            return new NBTTagCompound();  // 文件不存在时返回空的 NBTTagCompound
        }

        try {
            // 从文件中读取 JSON 字符串
            String fileContent = new String(Files.readAllBytes(filePath));
            JSONObject jsonObject = new JSONObject(fileContent);

            // 将 JSON 转换为 NBTTagCompound
            return NBTJsonConverter.jsonToNbt(jsonObject);
        } catch (IOException e) {
            System.err.println("读取文件失败: " + filePath);
            e.printStackTrace();
        }

        return new NBTTagCompound();  // 读取失败时返回空的 NBTTagCompound
    }
}