package com.haha.xiuxian.util.basic;

import net.minecraft.nbt.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;

public class NBTJsonConverter {

    // 将 NBTTagCompound 转换为 JSONObject
    public static JSONObject nbtToJson(NBTTagCompound compound) {
        JSONObject jsonObject = new JSONObject();
        Set<String> keys = compound.getKeySet();

        for (String key : keys) {
            NBTBase base = compound.getTag(key);

            if (base instanceof NBTTagCompound) {
                jsonObject.put(key, nbtToJson((NBTTagCompound) base));
            } else if (base instanceof NBTTagList) {
                jsonObject.put(key, nbtListToJsonArray((NBTTagList) base));
            } else if (base instanceof NBTTagString) {
                jsonObject.put(key, ((NBTTagString) base).getString());
            } else if (base instanceof NBTTagInt) {
                jsonObject.put(key, ((NBTTagInt) base).getInt());
            } else if (base instanceof NBTTagDouble) {
                jsonObject.put(key, ((NBTTagDouble) base).getDouble());
            } else if (base instanceof NBTTagFloat) {
                jsonObject.put(key, ((NBTTagFloat) base).getFloat());
            } else if (base instanceof NBTTagLong) {
                jsonObject.put(key, ((NBTTagLong) base).getLong());
            } else if (base instanceof NBTTagShort) {
                jsonObject.put(key, ((NBTTagShort) base).getShort());
            } else if (base instanceof NBTTagByte) {
                jsonObject.put(key, ((NBTTagByte) base).getByte());
            } else if (base instanceof NBTTagByteArray) {
                jsonObject.put(key, ((NBTTagByteArray) base).getByteArray());
            } else if (base instanceof NBTTagIntArray) {
                jsonObject.put(key, ((NBTTagIntArray) base).getIntArray());
            } else {
                jsonObject.put(key, base.toString());
            }
        }
        return jsonObject;
    }

    // 将 NBTTagList 转换为 JSONArray
    private static JSONArray nbtListToJsonArray(NBTTagList list) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.tagCount(); i++) {
            NBTBase element = list.get(i);

            if (element instanceof NBTTagCompound) {
                jsonArray.put(nbtToJson((NBTTagCompound) element));
            } else if (element instanceof NBTTagString) {
                jsonArray.put(((NBTTagString) element).getString());
            } else if (element instanceof NBTTagInt) {
                jsonArray.put(((NBTTagInt) element).getInt());
            } else if (element instanceof NBTTagDouble) {
                jsonArray.put(((NBTTagDouble) element).getDouble());
            } else {
                jsonArray.put(element.toString());
            }
        }
        return jsonArray;
    }

    // 将 JSONObject 转换为 NBTTagCompound
    public static NBTTagCompound jsonToNbt(JSONObject jsonObject) {
        NBTTagCompound compound = new NBTTagCompound();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            if (value instanceof JSONObject) {
                compound.setTag(key, jsonToNbt((JSONObject) value));
            } else if (value instanceof JSONArray) {
                compound.setTag(key, jsonArrayToNbtList((JSONArray) value));
            } else if (value instanceof String) {
                compound.setString(key, (String) value);
            } else if (value instanceof Integer) {
                compound.setInteger(key, (Integer) value);
            } else if (value instanceof Double) {
                compound.setDouble(key, (Double) value);
            } else if (value instanceof Long) {
                compound.setLong(key, (Long) value);
            } else if (value instanceof Float) {
                compound.setFloat(key, (Float) value);
            }
        }
        return compound;
    }

    // 将 JSONArray 转换为 NBTTagList
    private static NBTTagList jsonArrayToNbtList(JSONArray jsonArray) {
        NBTTagList nbtList = new NBTTagList();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object element = jsonArray.get(i);

            if (element instanceof JSONObject) {
                nbtList.appendTag(jsonToNbt((JSONObject) element));
            } else if (element instanceof String) {
                nbtList.appendTag(new NBTTagString((String) element));
            } else if (element instanceof Integer) {
                nbtList.appendTag(new NBTTagInt((Integer) element));
            } else if (element instanceof Double) {
                nbtList.appendTag(new NBTTagDouble((Double) element));
            } else if (element instanceof Long) {
                nbtList.appendTag(new NBTTagLong((Long) element));
            } else if (element instanceof Float) {
                nbtList.appendTag(new NBTTagFloat((Float) element));
            }
        }
        return nbtList;
    }
}