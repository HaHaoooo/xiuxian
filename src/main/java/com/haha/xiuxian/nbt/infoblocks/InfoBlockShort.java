package com.haha.xiuxian.nbt.infoblocks;

public class InfoBlockShort implements InfoBlockBase<Short> {
    private short value;

    public InfoBlockShort(short value) {
        this.value = value;
    }

    @Override
    public Short getValue() {
        return value;
    }

    @Override
    public void setValue(Short value) {
        this.value = value;
    }
}
