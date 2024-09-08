package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockString implements InfoBlockBase<String> {
    private String value;

    public InfoBlockString(String value) {
        this.value = value;
    }

    @Override
    public String toJson() {
        return value;  // 直接返回实际值
    }

    @Override
    public void fromJson(String jsonData) {
        this.value = jsonData;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}