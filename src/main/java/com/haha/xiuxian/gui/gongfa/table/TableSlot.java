package com.haha.xiuxian.gui.gongfa.table;

import com.haha.xiuxian.items.gongfa.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TableSlot extends SlotItemHandler {
    public TableSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof EmptyGongFaBase
                || item instanceof MetalGongFaBase
                || item instanceof WoodGongFaBase
                || item instanceof WaterGongFaBase
                || item instanceof FireGongFaBase
                || item instanceof DirtGongFaBase;
    }
}
