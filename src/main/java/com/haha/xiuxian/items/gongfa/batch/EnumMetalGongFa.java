package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.MetalGongFaBase;
import net.minecraft.item.Item;

public enum EnumMetalGongFa {
    TEMPLATE("metal_gongfa");

    private final Item item;

    EnumMetalGongFa(String registryName) {
        this.item = new MetalGongFaBase(registryName);
    }

    public Item getItem() {
        return item;
    }
}
