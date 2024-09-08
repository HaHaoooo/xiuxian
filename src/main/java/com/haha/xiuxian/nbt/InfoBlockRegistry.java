package com.haha.xiuxian.nbt;

import com.haha.xiuxian.nbt.infoblocks.*;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class InfoBlockRegistry {
    public static final Map<Class<?>, Function<Object, InfoBlockBase<?>>> typeRegistry = new HashMap<>();

    static {
        registerType(String.class, val -> new InfoBlockString((String) val));
        registerType(Integer.class, val -> new InfoBlockInt((Integer) val));
        registerType(Double.class, val -> new InfoBlockDouble((Double) val));
        registerType(Long.class, val -> new InfoBlockLong((Long) val));
        registerType(Boolean.class, val -> new InfoBlockBoolean((Boolean) val));
        registerType(Short.class, val -> new InfoBlockShort((Short) val));
        registerType(Byte.class, val -> new InfoBlockByte((Byte) val));
        registerType(UUID.class, val -> new InfoBlockUUID((UUID) val));
        registerType(URL.class, val -> new InfoBlockURL((URL) val));
        registerType(Duration.class, val -> new InfoBlockDuration((Duration) val));
        registerType(Color.class, val -> new InfoBlockColor((Color) val));
        registerType(BigDecimal.class, val -> new InfoBlockBigDecimal((BigDecimal) val));
        registerType(HashMap.class, val -> {
           @SuppressWarnings("unchecked")
            HashMap<String, Object> mapValue = (HashMap<String, Object>) val;
            InfoBlockCompound nestedCompound = new InfoBlockCompound();
            nestedCompound.setValue(mapValue);
            return nestedCompound;
        });
        registerType(ArrayList.class, val -> {
             @SuppressWarnings("unchecked")
             ArrayList<Object> listValue = (ArrayList<Object>) val;
            InfoBlockList nestedList = new InfoBlockList();
            nestedList.setValue(listValue);
            return nestedList;
        });
    }

    // 公共方法，用于注册新类
    public static <T> void registerType(Class<T> clazz, Function<Object, InfoBlockBase<?>> constructor) {
        typeRegistry.put(clazz, constructor);
    }
}
