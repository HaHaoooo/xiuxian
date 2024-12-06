package com.haha.xiuxian;

import com.haha.xiuxian.util.basic.NBTJsonConverter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.UUID;

public class NbtWriteAndReadTest {

    private static final String directoryPath = System.getProperty("user.dir") + "/src/test/resources/assets/file";
    private static final String filePath = directoryPath + "/nbt.json";

    public static void main(String[] args) throws IOException {
        write();
        read();
    }

    private static void read() throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(fileContent);

        NBTTagCompound compound = NBTJsonConverter.jsonToNbt(jsonObject);
        String name = compound.getString("name");

        NBTTagCompound nest = compound.getCompoundTag("nest");
        int color = compound.getInteger("color");

        NBTTagList list = compound.getTagList("list", Constants.NBT.TAG_INT);
        String uuid = compound.getString("uuid");

        System.out.println(name);
        System.out.println(nest);
        System.out.println(color);
        System.out.println(list);
        System.out.println(uuid);
    }

    private static void write() throws IOException {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("name", "haha");
        compound.setInteger("color", Color.CYAN.getRGB());
        compound.setString("uuid", UUID.randomUUID().toString());

        // NBTTagList 只能写进一种数据类型
        NBTTagList list = new NBTTagList();
        list.appendTag(new NBTTagInt(111231));
        list.appendTag(new NBTTagInt(222));
        list.appendTag(new NBTTagInt(333));

        NBTTagCompound nest = new NBTTagCompound();
        nest.setInteger("location", 1231);
        nest.setString("duration", Duration.ofSeconds(30).toString());
        nest.setBoolean("haha", true);

        compound.setTag("list", list);
        compound.setTag("nest", nest);

        JSONObject mainObject = NBTJsonConverter.nbtToJson(compound);
        BufferedWriter file = new BufferedWriter(new FileWriter(filePath));
        file.write(mainObject.toString(4));  // 格式化 JSON 输出
        file.flush();
        file.close();
    }
}
