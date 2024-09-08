package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.WaterGongFaBase;
import net.minecraft.item.Item;

public enum EnumWaterGongFa {
    TEMPLATE("water_gongfa"),
    LIAN_HUA_MIAO_DIAN("lian_hua_miao_dian");

    private final Item item;

    EnumWaterGongFa(String registryName) {
        this.item = new WaterGongFaBase(registryName);
    }

    public Item getItem() {
        return item;
    }
}
