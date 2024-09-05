package com.haha.xiuxian;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src","test", "resources", "assets", "gongfa");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, "*.json")) {
            for (Path filePath : directoryStream) {
                JSONObject jsonObject = readJsonFile(filePath);

                String name = jsonObject.getString("name");
                JSONArray description = jsonObject.getJSONArray("description");
                JSONObject properties = jsonObject.getJSONObject("properties");

                System.out.println("-----------[" + filePath.getFileName() + "]-----------");

                // 名称打印
                System.out.println("| " + name);

                // 描述打印
                description.forEach(s -> System.out.println("| " + s));

                // 属性打印 (这个打印的是键值对)
                properties.keys().forEachRemaining(key -> {
                    Object value = properties.get(key);
                    System.out.println("| " + key + ": " + value);
                });
            }
        }
    }

    private static JSONObject readJsonFile(Path filePath) throws IOException {
        String content = new String(Files.readAllBytes(filePath));
        return new JSONObject(content);
    }
}
