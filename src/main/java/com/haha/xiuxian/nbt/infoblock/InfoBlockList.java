package com.haha.xiuxian.nbt.infoblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoBlockList implements InfoBlockBase<List<Object>> {
    private final List<InfoBlockBase<?>> list = new ArrayList<>();

    @Override
    public List<Object> toJson() {
        List<Object> jsonList = new ArrayList<>();
        for (InfoBlockBase<?> element : list) {
            jsonList.add(element.toJson());
        }
        return jsonList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void fromJson(List<Object> jsonData) {
        list.clear();
        for (Object obj : jsonData) {
            if (obj instanceof String) {
                list.add(new InfoBlockString((String) obj));
            } else if (obj instanceof Integer) {
                list.add(new InfoBlockInt((Integer) obj));
            } else if (obj instanceof Double) {
                list.add(new InfoBlockDouble((Double) obj));
            } else if (obj instanceof Long) {
                list.add(new InfoBlockLong((Long) obj));
            } else if (obj instanceof Boolean) {
                list.add(new InfoBlockBoolean((Boolean) obj));
            } else if (obj instanceof Short) {
                list.add(new InfoBlockShort((Short) obj));
            } else if (obj instanceof Byte) {
                list.add(new InfoBlockByte((Byte) obj));
            } else if (obj instanceof List) {
                InfoBlockList nestedList = new InfoBlockList();
                nestedList.fromJson((List<Object>) obj);
                list.add(nestedList);
            } else if (obj instanceof Map) {
                InfoBlockCompound nestedCompound = new InfoBlockCompound();
                nestedCompound.fromJson((Map<String, Object>) obj);
                list.add(nestedCompound);
            } else {
                throw new IllegalArgumentException("Unsupported type in fromJson: " + obj.getClass().getName());
            }
        }
    }

    @Override
    public List<Object> getValue() {
        List<Object> values = new ArrayList<>();
        for (InfoBlockBase<?> element : list) {
            values.add(element.getValue());
        }
        return values;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setValue(List<Object> value) {
        list.clear();
        for (Object obj : value) {
            if (obj instanceof String) {
                list.add(new InfoBlockString((String) obj));
            } else if (obj instanceof Integer) {
                list.add(new InfoBlockInt((Integer) obj));
            } else if (obj instanceof Double) {
                list.add(new InfoBlockDouble((Double) obj));
            } else if (obj instanceof Long) {
                list.add(new InfoBlockLong((Long) obj));
            } else if (obj instanceof Boolean) {
                list.add(new InfoBlockBoolean((Boolean) obj));
            } else if (obj instanceof Short) {
                list.add(new InfoBlockShort((Short) obj));
            } else if (obj instanceof Byte) {
                list.add(new InfoBlockByte((Byte) obj));
            } else if (obj instanceof List) {
                InfoBlockList nestedList = new InfoBlockList();
                nestedList.setValue((List<Object>) obj);
                list.add(nestedList);
            } else if (obj instanceof Map) {
                InfoBlockCompound nestedCompound = new InfoBlockCompound();
                nestedCompound.setValue((Map<String, Object>) obj);
                list.add(nestedCompound);
            }
        }
    }

    // 添加元素
    public void add(InfoBlockBase<?> element) {
        list.add(element);
    }
}