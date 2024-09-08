package com.haha.xiuxian.nbt.infoblock;

public interface InfoBlockBase<T> {
    T toJson();

    void fromJson(T jsonData);

    T getValue();

    void setValue(T value);
}