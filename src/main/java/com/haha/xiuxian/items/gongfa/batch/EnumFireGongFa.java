package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.FireGongFaBase;
import net.minecraft.item.Item;

public enum EnumFireGongFa {
    TEMPLATE("template.json", "fire_gongfa");

    private final Item item;
    EnumFireGongFa(String fileName, String registryName){
        this.item = new FireGongFaBase(fileName, registryName);
    }

    public Item getItem() {
        return item;
    }
}
