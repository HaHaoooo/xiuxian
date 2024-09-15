package com.haha.xiuxian.items.gongfa.batch;

import com.haha.xiuxian.items.gongfa.EmptyGongFaBase;
import net.minecraft.item.Item;

public enum EnumEmptyGongFa implements IGongFaEnum{
    TEMPLATE("empty_gongfa");

    private final Item item;

    EnumEmptyGongFa(String registryName) {
        this.item = new EmptyGongFaBase(registryName);
    }

    @Override
    public Item getItem() {
        return item;
    }
}
