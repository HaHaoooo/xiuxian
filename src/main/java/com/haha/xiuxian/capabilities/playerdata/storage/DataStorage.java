package com.haha.xiuxian.capabilities.playerdata.storage;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class DataStorage implements Capability.IStorage<DataContainer> {

    public static final EnumFacing capaSide = EnumFacing.UP;
    @Override
    public NBTBase writeNBT(Capability<DataContainer> capability, DataContainer instance, EnumFacing side) {
        if(side == capaSide) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setDouble("lingli", instance.getLingLi());
            tagCompound.setDouble("lingliMax", instance.getLingLiMax());
            tagCompound.setDouble("metal", instance.getMetal());
            tagCompound.setDouble("metalMax", instance.getMetalMax());
            tagCompound.setDouble("wood", instance.getWood());
            tagCompound.setDouble("woodMax", instance.getWoodMax());
            tagCompound.setDouble("water", instance.getWater());
            tagCompound.setDouble("waterMax", instance.getWaterMax());
            tagCompound.setDouble("fire", instance.getFire());
            tagCompound.setDouble("fireMax", instance.getFireMax());
            tagCompound.setDouble("dirt", instance.getDirt());
            tagCompound.setDouble("dirtMax", instance.getDirtMax());
            tagCompound.setBoolean("showGui", instance.getBooleanOfGui());
            tagCompound.setString("level", instance.getLevel());
            return tagCompound;
        } else {
            return new NBTTagCompound();
        }
    }

    @Override
    public void readNBT(Capability<DataContainer> capability, DataContainer instance, EnumFacing side, NBTBase nbt) {
        if(side == capaSide) {
            if (nbt instanceof NBTTagCompound) {
                NBTTagCompound tagCompound = (NBTTagCompound) nbt;
                instance.setLingLi(tagCompound.getDouble("lingli"));
                instance.setLingLiMax(tagCompound.getDouble("lingliMax"));
                instance.setMetal(tagCompound.getDouble("metal"));
                instance.setMetalMax(tagCompound.getDouble("metalMax"));
                instance.setWood(tagCompound.getDouble("wood"));
                instance.setWoodMax(tagCompound.getDouble("woodMax"));
                instance.setWater(tagCompound.getDouble("water"));
                instance.setWaterMax(tagCompound.getDouble("waterMax"));
                instance.setFire(tagCompound.getDouble("fire"));
                instance.setFireMax(tagCompound.getDouble("fireMax"));
                instance.setDirt(tagCompound.getDouble("dirt"));
                instance.setDirtMax(tagCompound.getDouble("dirtMax"));
                instance.showGui(tagCompound.getBoolean("showGui"));
                instance.setLevel(tagCompound.getString("level"));
            }
        }
    }
}
