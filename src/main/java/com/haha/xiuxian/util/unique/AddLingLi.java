package com.haha.xiuxian.util.unique;

import com.haha.xiuxian.capabilities.playerdata.DataInject;
import com.haha.xiuxian.capabilities.playerdata.IDataContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class AddLingLi {
    private static final IDataContainer dataContainer = DataInject.DataContainer;

    private static void addAttribute(double value, EntityPlayer player, World world, ItemStack itemStack,
                                     AttributeGetter getter, AttributeSetter setter, AttributeMaxGetter maxGetter) {
        if (!world.isRemote) {
            if (getter.get() < maxGetter.get()) {
                double lingli = getter.get() + value / 2;
                setter.set(lingli);
                sendPlayerMessage(player, "手动吸收，杂质存留，吸收率仅50%", world);
                itemStack.shrink(1);
            } else {
                sendPlayerMessage(player, "此属性灵根已满，无法吸收", world);
            }
        }
    }

    public static void addMetal(double value, EntityPlayer player, World world, ItemStack itemStack) {
        addAttribute(value, player, world, itemStack, dataContainer::getMetal, dataContainer::setMetal, dataContainer::getMetalMax);
    }

    public static void addWood(double value, EntityPlayer player, World world, ItemStack itemStack) {
        addAttribute(value, player, world, itemStack, dataContainer::getWood, dataContainer::setWood, dataContainer::getWoodMax);
    }

    public static void addWater(double value, EntityPlayer player, World world, ItemStack itemStack) {
        addAttribute(value, player, world, itemStack, dataContainer::getWater, dataContainer::setWater, dataContainer::getWaterMax);
    }

    public static void addFire(double value, EntityPlayer player, World world, ItemStack itemStack) {
        addAttribute(value, player, world, itemStack, dataContainer::getFire, dataContainer::setFire, dataContainer::getFireMax);
    }

    public static void addDirt(double value, EntityPlayer player, World world, ItemStack itemStack) {
        addAttribute(value, player, world, itemStack, dataContainer::getDirt, dataContainer::setDirt, dataContainer::getDirtMax);
    }

    public static void sendPlayerMessage(EntityPlayer player, String message, World world) {
        if (!world.isRemote) {
            TextComponentString textComponent = new TextComponentString(message);
            player.sendMessage(textComponent);
        }
    }

    // 函数式接口定义
    @FunctionalInterface
    interface AttributeGetter {
        double get();
    }

    @FunctionalInterface
    interface AttributeSetter {
        void set(double value);
    }

    @FunctionalInterface
    interface AttributeMaxGetter {
        double get();
    }
}