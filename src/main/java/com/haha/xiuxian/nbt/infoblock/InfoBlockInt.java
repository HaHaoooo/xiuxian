package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockInt implements InfoBlockBase<Integer> {
    private int value;

    public InfoBlockInt(int value) {
        this.value = value;
    }

    @Override
    public Integer toJson() {
        return value;  // 直接返回实际值
    }

    @Override
    public void fromJson(Integer jsonData) {
        this.value = jsonData;
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