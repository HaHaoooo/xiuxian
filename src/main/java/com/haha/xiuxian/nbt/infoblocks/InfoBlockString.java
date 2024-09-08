package com.haha.xiuxian.nbt.infoblocks;

import java.awt.*;

public class InfoBlockString implements InfoBlockBase<String> {
    private String value;

    public InfoBlockString(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public InfoBlockColor toColor() {
        try {
            return new InfoBlockColor(Color.decode(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid color format: " + value);
        }
    }
}