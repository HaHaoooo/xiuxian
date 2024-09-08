package com.haha.xiuxian;

import com.haha.xiuxian.nbt.infoblocks.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.UUID;

public class InfoBlockTest {

    private static final String directoryPath = System.getProperty("user.dir") + "/src/test/resources/assets/file";
    private static final String filePath = directoryPath + "/infoBlock.json";

    public static void main(String[] args) throws IOException {
        write();
        read();
    }

    private static void read() throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(fileContent);

        InfoBlockCompound compound = new InfoBlockCompound();
        compound.setValue((HashMap<String, Object>) jsonObject.toMap());
        InfoBlockString name = compound.get("name", InfoBlockString.class);
        InfoBlockCompound nest = compound.get("nest", InfoBlockCompound.class);
        InfoBlockColor color = compound.get("color", InfoBlockColor.class);
        InfoBlockList list = compound.get("list", InfoBlockList.class);
        InfoBlockUUID uuid = compound.get("uuid", InfoBlockUUID.class);

        System.out.println(name.getValue());
        System.out.println(nest.getValue());
        System.out.println(color.getValue());
        System.out.println(list.getValue());
        System.out.println(uuid.getValue());
    }

    private static void write() throws IOException {
        InfoBlockCompound compound = new InfoBlockCompound();
        compound.put("name", new InfoBlockString("haha"));
        compound.put("color", new InfoBlockColor(Color.CYAN));
        compound.put("uuid", new InfoBlockUUID(UUID.randomUUID()));
        InfoBlockList list = new InfoBlockList();
        list.add(new InfoBlockInt(1223214124));
        list.add(new InfoBlockByte(Byte.MAX_VALUE));
        list.add(new InfoBlockDouble(35.123));
        InfoBlockCompound nest = new InfoBlockCompound();
        nest.put("location", new InfoBlockInt(1231));
        nest.put("duration", new InfoBlockDuration(Duration.ofSeconds(30)));
        nest.put("haha", new InfoBlockBoolean(true));
        compound.put("list", list);
        compound.put("nest", nest);

        JSONObject mainObject = new JSONObject(compound.getValue());
        BufferedWriter file = new BufferedWriter(new FileWriter(filePath));
        file.write(mainObject.toString(4));
        file.flush();
        System.out.println("JSON written to " + filePath);
    }
}
