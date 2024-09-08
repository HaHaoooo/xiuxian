package com.haha.xiuxian.nbt.infoblocks;

import java.net.MalformedURLException;
import java.net.URL;

public class InfoBlockURL implements InfoBlockBase<String> {
    private URL value;

    public InfoBlockURL(URL value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    @Override
    public void setValue(String value) {
        try {
            this.value = new URL(value);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL format", e);
        }
    }
}