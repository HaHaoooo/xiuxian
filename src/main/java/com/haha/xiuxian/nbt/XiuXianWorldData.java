package com.haha.xiuxian.nbt;

import com.haha.xiuxian.nbt.infoblocks.InfoBlockCompound;
import com.haha.xiuxian.util.basic.world.WorldUtil;
import net.minecraft.world.World;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class XiuXianWorldData {

    private final Path directoryPath;
    private final Path filePath;

    public XiuXianWorldData(String name, World world) {
        this.directoryPath = Paths.get(WorldUtil.getWorldDirectoryName(world), "xiuxian");
        this.filePath = directoryPath.resolve(name + ".json");
    }

    // 写入 InfoBlock 数据到文件
    public void write(InfoBlockCompound newData) {
        try {
            Files.createDirectories(directoryPath);
        } catch (IOException e) {
            System.err.println("无法创建文件夹: " + directoryPath);
            e.printStackTrace();
            return;
        }

        // 读取
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

        // 合并
        JSONObject newDataJson = new JSONObject(newData.getValue());
        for (String key : newDataJson.keySet()) {
            existingData.put(key, newDataJson.get(key));
        }

        // 更新
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            fileWriter.write(existingData.toString(4));  // 格式化 JSON 输出
            fileWriter.flush();
            System.out.println("已成功写入文件: " + filePath);
        } catch (IOException e) {
            System.err.println("写入文件失败: " + filePath);
            e.printStackTrace();
        }
    }

    // 读取 InfoBlock 数据
    public InfoBlockCompound get() {
        if (!Files.exists(filePath)) {
            System.err.println("文件不存在: " + filePath);
            return new InfoBlockCompound();
        }
        try {
            // 转换值Json
            String fileContent = new String(Files.readAllBytes(filePath));
            JSONObject jsonObject = new JSONObject(fileContent);
            InfoBlockCompound infoBlockCompound = new InfoBlockCompound();
            infoBlockCompound.setValue((HashMap<String, Object>) jsonObject.toMap());
            return infoBlockCompound;
        } catch (IOException e) {
            System.err.println("读取文件失败: " + filePath);
            e.printStackTrace();
        }
        return new InfoBlockCompound();  // 异常时返回空的 InfoBlockCompound
    }
}