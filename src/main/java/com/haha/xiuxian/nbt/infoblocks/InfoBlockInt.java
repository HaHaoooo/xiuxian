package com.haha.xiuxian.nbt.infoblocks;

public class InfoBlockInt implements InfoBlockBase<Integer> {
    private int value;

    public InfoBlockInt(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}