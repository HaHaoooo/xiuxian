package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.EmptyGongFaBase;
import net.minecraft.item.Item;

public enum EnumEmptyGongFa {
    TEMPLATE("template.json", "empty_gongfa");

    private final Item item;
    EnumEmptyGongFa(String fileName, String registryName){
        this.item = new EmptyGongFaBase(fileName, registryName);
    }

    public Item getItem() {
        return item;
    }
}
