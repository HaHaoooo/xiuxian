package com.haha.xiuxian.nbt.infoblocks;

import java.time.Duration;

public class InfoBlockDuration implements InfoBlockBase<Duration>{
    private Duration value;

    public InfoBlockDuration(Duration value) {
        this.value = value;
    }

    @Override
    public Duration getValue() {
        return value;
    }

    @Override
    public void setValue(Duration value) {
        this.value = value;
    }
}
