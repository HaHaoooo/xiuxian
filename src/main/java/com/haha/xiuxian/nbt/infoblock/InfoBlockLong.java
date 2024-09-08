package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockLong implements InfoBlockBase<Long> {
    private long value;

    public InfoBlockLong(long value) {
        this.value = value;
    }

    @Override
    public Long toJson() {
        return value;  // 返回实际值
    }

    @Override
    public void fromJson(Long jsonData) {
        this.value = jsonData;
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