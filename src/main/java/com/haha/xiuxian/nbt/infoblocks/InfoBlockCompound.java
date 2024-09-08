package com.haha.xiuxian.nbt.infoblocks;

import com.haha.xiuxian.nbt.InfoBlockRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class InfoBlockCompound implements InfoBlockBase<HashMap<String, Object>> {
    private final Map<String, InfoBlockBase<?>> compound = new HashMap<>();

    @Override
    public HashMap<String, Object> getValue() {
        HashMap<String, Object> values = new HashMap<>();
        for (HashMap.Entry<String, InfoBlockBase<?>> entry : compound.entrySet()) {
            values.put(entry.getKey(), entry.getValue().getValue());
        }
        return values;
    }

    @Override
    public void setValue(HashMap<String, Object> value) {
        compound.clear();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            Object val = entry.getValue();
            Function<Object, InfoBlockBase<?>> constructor = InfoBlockRegistry.typeRegistry.get(val.getClass());
            if (constructor != null) {
                compound.put(entry.getKey(), constructor.apply(val));
            } else {
                throw new IllegalArgumentException("Unsupported type in setValue: " + val.getClass().getName());
            }
        }
    }

    public void put(String key, InfoBlockBase<?> value) {
        compound.put(key, value);
    }

    public <T extends InfoBlockBase<?>> T get(String key, Class<T> type) {
        InfoBlockBase<?> value = compound.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Key '" + key + "' does not exist in the compound.");
        }

        if (type == InfoBlockColor.class && value instanceof InfoBlockString) {
            InfoBlockColor colorBlock = ((InfoBlockString) value).toColor();
            return type.cast(colorBlock);
        }

        if (type == InfoBlockUUID.class && value instanceof InfoBlockString) {
            InfoBlockUUID uuidBlock = new InfoBlockUUID(UUID.fromString(((InfoBlockString) value).getValue()));
            return type.cast(uuidBlock);
        }
        return type.cast(value);
    }

    public boolean hasKey(String key) {
        return compound.containsKey(key);
    }
}