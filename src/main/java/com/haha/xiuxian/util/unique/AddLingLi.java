package com.haha.xiuxian.util.unique;

import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.capabilities.playerdata.storage.DataContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class AddLingLi {
    private final static DataContainer dataContainer = DataInject.DataContainer;
    public static void addMetal(double value, EntityPlayer player, World world, ItemStack itemStack) {
        if (!world.isRemote) {
            if (dataContainer.getMetal() < dataContainer.getMetalMax()) {
                double lingli = dataContainer.getMetal() + value / 2;
                dataContainer.setMetal(lingli);
                sendPlayerMessage(player, "手动吸收，杂质存留，吸收率仅50%", world);
                itemStack.shrink(1);
            } else {
                sendPlayerMessage(player, "此属性灵根已满，无法吸收", world);
            }
        }
    }

    public static void addWood(double value, EntityPlayer player, World world, ItemStack itemStack){
        if (!world.isRemote) {
            if (dataContainer.getWood() < dataContainer.getWoodMax()) {
                double lingli = dataContainer.getWood() + value / 2;
                dataContainer.setWood(lingli);
                sendPlayerMessage(player, "手动吸收，杂质存留，吸收率仅50%", world);
                itemStack.shrink(1);
            } else {
                sendPlayerMessage(player, "此属性灵根已满，无法吸收", world);
            }
        }
    }

    public static void addWater(double value, EntityPlayer player, World world, ItemStack itemStack){
        if (!world.isRemote) {
            if (dataContainer.getWater() < dataContainer.getWaterMax()) {
                double lingli = dataContainer.getWater() + value / 2;
                dataContainer.setWater(lingli);
                sendPlayerMessage(player, "手动吸收，杂质存留，吸收率仅50%", world);
                itemStack.shrink(1);
            } else {
                sendPlayerMessage(player, "此属性灵根已满，无法吸收", world);
            }
        }
    }

    public static void addFire(double value, EntityPlayer player, World world, ItemStack itemStack){
        if (!world.isRemote) {
            if (dataContainer.getFire() < dataContainer.getFireMax()) {
                double lingli = dataContainer.getFire() + value / 2;
                dataContainer.setFire(lingli);
                sendPlayerMessage(player, "手动吸收，杂质存留，吸收率仅50%", world);
                itemStack.shrink(1);
            } else {
                sendPlayerMessage(player, "此属性灵根已满，无法吸收", world);
            }
        }
    }

    public static void addDirt(double value, EntityPlayer player, World world, ItemStack itemStack){
        if (!world.isRemote) {
            if (dataContainer.getDirt() < dataContainer.getDirtMax()) {
                double lingli = dataContainer.getDirt() + value / 2;
                dataContainer.setDirt(lingli);
                sendPlayerMessage(player, "手动吸收，杂质存留，吸收率仅50%", world);
                itemStack.shrink(1);
            } else {
                sendPlayerMessage(player, "此属性灵根已满，无法吸收", world);
            }
        }
    }

    public static void sendPlayerMessage(EntityPlayer player, String message, World world) {
        if (!world.isRemote) {
            TextComponentString textComponent = new TextComponentString(message);
            player.sendMessage(textComponent);
        }
    }
}
