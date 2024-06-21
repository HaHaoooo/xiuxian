package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.MetalGongFaBase;
import net.minecraft.item.Item;

public enum EnumMetalGongFa {
    TEMPLATE("template.json", "metal_gongfa");

    private final Item item;
    EnumMetalGongFa(String fileName, String registryName){
        this.item = new MetalGongFaBase(fileName, registryName);
    }

    public Item getItem() {
        return item;
    }
}
