package com.haha.xiuxian.nbt.infoblocks;

public class InfoBlockBoolean implements InfoBlockBase<Boolean> {
    private boolean value;

    public InfoBlockBoolean(boolean value) {
        this.value = value;
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