package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.WoodGongFaBase;
import net.minecraft.item.Item;

public enum EnumWoodGongFa {
    TEMPLATE("wood_gongfa");

    private final Item item;
    EnumWoodGongFa(String registryName){
        this.item = new WoodGongFaBase(registryName);
    }

    public Item getItem() {
        return item;
    }
}
