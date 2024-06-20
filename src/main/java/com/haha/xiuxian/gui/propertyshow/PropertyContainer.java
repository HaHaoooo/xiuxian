package com.haha.xiuxian.gui.propertyshow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import javax.annotation.Nonnull;

public class PropertyContainer extends Container {
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }
}
