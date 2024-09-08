package com.haha.xiuxian.nbt.infoblocks;

import java.math.BigDecimal;

public class InfoBlockBigDecimal implements InfoBlockBase<String> {
    private BigDecimal value;

    public InfoBlockBigDecimal(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value.toPlainString();
    }

    @Override
    public void setValue(String value) {
        this.value = new BigDecimal(value);
    }
}