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
                jsonObject.put(key, nbtToJson((NBTTagCompound) base));  // 递归处理子 Compound
            } else if (base instanceof NBTTagList) {
                jsonObject.put(key, nbtListToJsonArray((NBTTagList) base));  // 将 NBTTagList 转换为 JSONArray
            } else if (base instanceof NBTTagString) {
                jsonObject.put(key, ((NBTTagString) base).getString());  // 字符串类型
            } else if (base instanceof NBTTagInt) {
                jsonObject.put(key, ((NBTTagInt) base).getInt());  // 整数类型
            } else if (base instanceof NBTTagDouble) {
                jsonObject.put(key, ((NBTTagDouble) base).getDouble());  // 双精度类型
            } else if (base instanceof NBTTagByte) {
                jsonObject.put(key, ((NBTTagByte) base).getByte());  // 字节类型
            } else if (base instanceof NBTTagFloat) {
                jsonObject.put(key, ((NBTTagFloat) base).getFloat());  // 浮点类型
            } else if (base instanceof NBTTagLong) {
                jsonObject.put(key, ((NBTTagLong) base).getLong());  // 长整型
            } else if (base instanceof NBTTagShort) {
                jsonObject.put(key, ((NBTTagShort) base).getShort());  // 短整型
            } else if (base instanceof NBTTagByteArray) {
                jsonObject.put(key, ((NBTTagByteArray) base).getByteArray());  // 字节数组
            } else if (base instanceof NBTTagIntArray) {
                jsonObject.put(key, ((NBTTagIntArray) base).getIntArray());  // 整数数组
            } else {
                // 其他类型作为字符串处理
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
                jsonArray.put(nbtToJson((NBTTagCompound) element));  // 递归处理子 Compound
            } else if (element instanceof NBTTagString) {
                jsonArray.put(((NBTTagString) element).getString());  // 字符串类型
            } else if (element instanceof NBTTagInt) {
                jsonArray.put(((NBTTagInt) element).getInt());  // 整数类型
            } else if (element instanceof NBTTagDouble) {
                jsonArray.put(((NBTTagDouble) element).getDouble());  // 双精度类型
            } else if (element instanceof NBTTagByte) {
                jsonArray.put(((NBTTagByte) element).getByte());  // 字节类型
            } else if (element instanceof NBTTagFloat) {
                jsonArray.put(((NBTTagFloat) element).getFloat());  // 浮点类型
            } else if (element instanceof NBTTagLong) {
                jsonArray.put(((NBTTagLong) element).getLong());  // 长整型
            } else if (element instanceof NBTTagShort) {
                jsonArray.put(((NBTTagShort) element).getShort());  // 短整型
            } else {
                jsonArray.put(element.toString());  // 其他类型作为字符串处理
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
                compound.setTag(key, jsonToNbt((JSONObject) value));  // 递归处理子 Compound
            } else if (value instanceof JSONArray) {
                compound.setTag(key, jsonArrayToNbtList((JSONArray) value));  // 处理 JSONArray
            } else if (value instanceof String) {
                compound.setString(key, (String) value);  // 字符串类型
            } else if (value instanceof Integer) {
                compound.setInteger(key, (Integer) value);  // 整数类型
            } else if (value instanceof Double) {
                compound.setDouble(key, (Double) value);  // 双精度类型
            } else if (value instanceof Long) {
                compound.setLong(key, (Long) value);  // 长整型
            } else if (value instanceof Float) {
                compound.setFloat(key, (Float) value);  // 浮点类型
            } else if (value instanceof Byte) {
                compound.setByte(key, (Byte) value);  // 字节类型
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
                nbtList.appendTag(jsonToNbt((JSONObject) element));  // 递归处理子 Compound
            } else if (element instanceof String) {
                nbtList.appendTag(new NBTTagString((String) element));  // 字符串类型
            } else if (element instanceof Integer) {
                nbtList.appendTag(new NBTTagInt((Integer) element));  // 整数类型
            } else if (element instanceof Double) {
                nbtList.appendTag(new NBTTagDouble((Double) element));  // 双精度类型
            } else if (element instanceof Long) {
                nbtList.appendTag(new NBTTagLong((Long) element));  // 长整型
            } else if (element instanceof Float) {
                nbtList.appendTag(new NBTTagFloat((Float) element));  // 浮点类型
            } else if (element instanceof Byte) {
                nbtList.appendTag(new NBTTagByte((Byte) element));  // 字节类型
            }
        }
        return nbtList;
    }
}