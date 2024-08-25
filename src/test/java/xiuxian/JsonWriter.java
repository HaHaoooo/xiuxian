package xiuxian;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class JsonWriter {
    public static void main(String[] args) {
        int num = 5;
        String directoryPath = System.getProperty("user.dir") + "/src/test/resources/assets/file";
        String filePath = directoryPath + "/output.json";

        // 创建文件夹（如果不存在）
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.out.println("Failed to create directory: " + directoryPath);
                return;
            }
        } else {
            System.out.println("Directory already created");
        }

        Map<String, JSONObject> all = new LinkedHashMap<>();
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            JSONObject item = new JSONObject();
            item.put("exp", i * 10);

            JSONArray levels = new JSONArray();
            levels.put("入门");
            levels.put("小成");
            levels.put("大成");
            levels.put("圆满");
            levels.put("超脱");
            levels.put("终焉");

            item.put("levels", levels);

            int randomIndex = random.nextInt(levels.length());
            item.put("currentLevel", levels.getString(randomIndex));
            item.put("currentId", randomIndex);

            all.put("itemName_" + i, item);
        }

        writeJsonToFile(all, filePath);
    }

    // 写入 JSON 到文件
    private static void writeJsonToFile(Map<String, JSONObject> object, String fileName) {
        JSONObject mainObject = new JSONObject(object);
        try (BufferedWriter file = new BufferedWriter(new FileWriter(fileName))) {
            file.write(mainObject.toString(4));
            file.flush();
            System.out.println("JSON written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
