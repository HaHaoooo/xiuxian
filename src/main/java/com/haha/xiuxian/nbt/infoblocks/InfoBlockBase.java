package com.haha.xiuxian.nbt.infoblocks;

public interface InfoBlockBase<T> {

    T getValue();

    void setValue(T value);
}