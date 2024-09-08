package com.haha.xiuxian.nbt.infoblocks;

import java.util.UUID;

public class InfoBlockUUID implements InfoBlockBase<String> {
    private UUID uuid;

    public InfoBlockUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getValue() {
        return uuid.toString();
    }

    @Override
    public void setValue(String value) {
        this.uuid = UUID.fromString(value);
    }
}