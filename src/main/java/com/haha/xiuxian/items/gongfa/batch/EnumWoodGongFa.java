package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.WoodGongFaBase;
import net.minecraft.item.Item;

public enum EnumWoodGongFa {
    TEMPLATE("template.json", "wood_gongfa");

    private final Item item;
    EnumWoodGongFa(String fileName, String registryName){
        this.item = new WoodGongFaBase(fileName, registryName);
    }

    public Item getItem() {
        return item;
    }
}
