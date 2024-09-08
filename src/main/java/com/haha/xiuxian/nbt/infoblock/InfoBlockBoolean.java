package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockBoolean implements InfoBlockBase<Boolean> {
    private boolean value;

    public InfoBlockBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean toJson() {
        return value;  // 返回实际值
    }

    @Override
    public void fromJson(Boolean jsonData) {
        this.value = jsonData;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}