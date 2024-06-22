package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.FireGongFaBase;
import net.minecraft.item.Item;

public enum EnumFireGongFa {
    TEMPLATE("fire_gongfa");

    private final Item item;
    EnumFireGongFa(String registryName){
        this.item = new FireGongFaBase(registryName);
    }

    public Item getItem() {
        return item;
    }
}
