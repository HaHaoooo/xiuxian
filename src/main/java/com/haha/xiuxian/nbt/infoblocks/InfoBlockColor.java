package com.haha.xiuxian.nbt.infoblocks;

import java.awt.*;

public class InfoBlockColor implements InfoBlockBase<String> {
    private Color value;

    public InfoBlockColor(Color value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return String.format("#%02x%02x%02x", value.getRed(), value.getGreen(), value.getBlue());
    }

    @Override
    public void setValue(String value) {
        this.value = Color.decode(value);
    }

    public Color getColor() {
        return value;
    }

    public void setColor(Color color) {
        this.value = color;
    }
}
