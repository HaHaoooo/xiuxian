package com.haha.xiuxian.util.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileHelper {


    /**
     * @author haha
     * @param assets 输入resources/assets文件夹下的文件
     * @return 返回读取文件的信息
     * @throws IOException 抛出错误
     */
    public static String getResourceLocation(String assets) throws IOException {
        InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream("assets/" + assets);
        if (inputStream == null) {
            throw new IOException("Resource file not found: " + assets);
        }
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
