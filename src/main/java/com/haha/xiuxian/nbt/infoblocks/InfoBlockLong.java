package com.haha.xiuxian.nbt.infoblocks;

public class InfoBlockLong implements InfoBlockBase<Long> {
    private long value;

    public InfoBlockLong(long value) {
        this.value = value;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public void setValue(Long value) {
        this.value = value;
    }
}