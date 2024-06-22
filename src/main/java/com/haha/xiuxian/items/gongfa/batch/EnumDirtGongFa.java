package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.DirtGongFaBase;
import net.minecraft.item.Item;

public enum EnumDirtGongFa {
    TEMPLATE("dirt_gongfa");

    private final Item item;
    EnumDirtGongFa(String registryName){
        this.item = new DirtGongFaBase(registryName);
    }

    public Item getItem() {
        return item;
    }
}
