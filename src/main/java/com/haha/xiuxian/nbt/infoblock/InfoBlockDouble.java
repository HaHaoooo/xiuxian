package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockDouble implements InfoBlockBase<Double> {
    private double value;

    public InfoBlockDouble(double value) {
        this.value = value;
    }

    @Override
    public Double toJson() {
        return value;  // 直接返回实际值
    }

    @Override
    public void fromJson(Double jsonData) {
        this.value = jsonData;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}