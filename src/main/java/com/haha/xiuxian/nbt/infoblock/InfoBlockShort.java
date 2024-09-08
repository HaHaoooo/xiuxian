package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockShort implements InfoBlockBase<Short> {
    private short value;

    public InfoBlockShort(short value) {
        this.value = value;
    }

    @Override
    public Short toJson() {
        return value;
    }

    @Override
    public void fromJson(Short jsonData) {
        this.value = jsonData;
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
