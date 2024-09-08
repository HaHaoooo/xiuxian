package com.haha.xiuxian.nbt.infoblock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoBlockCompound implements InfoBlockBase<Map<String, Object>> {
    private final Map<String, InfoBlockBase<?>> compound = new HashMap<>();

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> jsonMap = new HashMap<>();
        for (Map.Entry<String, InfoBlockBase<?>> entry : compound.entrySet()) {
            jsonMap.put(entry.getKey(), entry.getValue().toJson());
        }
        return jsonMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void fromJson(Map<String, Object> jsonData) {
        compound.clear();
        for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                compound.put(entry.getKey(), new InfoBlockString((String) value));
            } else if (value instanceof Integer) {
                compound.put(entry.getKey(), new InfoBlockInt((Integer) value));
            } else if (value instanceof Double) {
                compound.put(entry.getKey(), new InfoBlockDouble((Double) value));
            } else if (value instanceof Long) {
                compound.put(entry.getKey(), new InfoBlockLong((Long) value));
            } else if (value instanceof Boolean) {
                compound.put(entry.getKey(), new InfoBlockBoolean((Boolean) value));
            } else if (value instanceof Short) {
                compound.put(entry.getKey(), new InfoBlockShort((Short) value));
            } else if (value instanceof Byte) {
                compound.put(entry.getKey(), new InfoBlockByte((Byte) value));
            } else if (value instanceof Map) {
                InfoBlockCompound nestedCompound = new InfoBlockCompound();
                nestedCompound.fromJson((Map<String, Object>) value);
                compound.put(entry.getKey(), nestedCompound);
            } else if (value instanceof List) {
                InfoBlockList nestedList = new InfoBlockList();
                nestedList.fromJson((List<Object>) value);
                compound.put(entry.getKey(), nestedList);
            } else {
                throw new IllegalArgumentException("Unsupported type in fromJson: " + value.getClass().getName());
            }
        }
    }

    @Override
    public Map<String, Object> getValue() {
        Map<String, Object> values = new HashMap<>();
        for (Map.Entry<String, InfoBlockBase<?>> entry : compound.entrySet()) {
            // 获取实际值，而不是封装的对象
            values.put(entry.getKey(), entry.getValue().getValue());
        }
        return values;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setValue(Map<String, Object> value) {
        compound.clear();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            Object val = entry.getValue();
            if (val instanceof String) {
                compound.put(entry.getKey(), new InfoBlockString((String) val));
            } else if (val instanceof Integer) {
                compound.put(entry.getKey(), new InfoBlockInt((Integer) val));
            } else if (val instanceof Double) {
                compound.put(entry.getKey(), new InfoBlockDouble((Double) val));
            } else if (val instanceof Long) {
                compound.put(entry.getKey(), new InfoBlockLong((Long) val));
            } else if (val instanceof Boolean) {
                compound.put(entry.getKey(), new InfoBlockBoolean((Boolean) val));
            } else if (val instanceof Short) {
                compound.put(entry.getKey(), new InfoBlockShort((Short) val));
            } else if (val instanceof Byte) {
                compound.put(entry.getKey(), new InfoBlockByte((Byte) val));
            } else if (val instanceof Map) {
                InfoBlockCompound nestedCompound = new InfoBlockCompound();
                nestedCompound.setValue((Map<String, Object>) val);
                compound.put(entry.getKey(), nestedCompound);
            } else if (val instanceof List) {
                InfoBlockList nestedList = new InfoBlockList();
                nestedList.setValue((List<Object>) val);
                compound.put(entry.getKey(), nestedList);
            }
        }
    }

    public void put(String key, InfoBlockBase<?> value) {
        compound.put(key, value);
    }

    public InfoBlockString getString(String key) {
        return (InfoBlockString) compound.get(key);
    }

    public InfoBlockInt getInt(String key) {
        return (InfoBlockInt) compound.get(key);
    }

    public InfoBlockDouble getDouble(String key) {
        return (InfoBlockDouble) compound.get(key);
    }

    public InfoBlockLong getLong(String key) {
        return (InfoBlockLong) compound.get(key);
    }

    public InfoBlockBoolean getBoolean(String key) {
        return (InfoBlockBoolean) compound.get(key);
    }

    public InfoBlockShort getShort(String key) {
        return (InfoBlockShort) compound.get(key);
    }

    public InfoBlockByte getByte(String key) {
        return (InfoBlockByte) compound.get(key);
    }

    public InfoBlockCompound getCompound(String key) {
        return (InfoBlockCompound) compound.get(key);
    }

    public InfoBlockList getList(String key) {
        return (InfoBlockList) compound.get(key);
    }

    public boolean hasKey(String key) {
        return compound.containsKey(key);
    }
}