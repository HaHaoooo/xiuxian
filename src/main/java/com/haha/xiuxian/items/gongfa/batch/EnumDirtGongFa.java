package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.DirtGongFaBase;
import net.minecraft.item.Item;

public enum EnumDirtGongFa {
    TEMPLATE("template.json", "dirt_gongfa");

    private final Item item;
    EnumDirtGongFa(String fileName, String registryName){
        this.item = new DirtGongFaBase(fileName, registryName);
    }

    public Item getItem() {
        return item;
    }
}
