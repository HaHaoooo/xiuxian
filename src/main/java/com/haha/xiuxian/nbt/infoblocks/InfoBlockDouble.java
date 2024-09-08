package com.haha.xiuxian.nbt.infoblocks;

public class InfoBlockDouble implements InfoBlockBase<Double> {
    private double value;

    public InfoBlockDouble(double value) {
        this.value = value;
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