package com.haha.xiuxian.capabilities.chunk;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DataStorage implements Capability.IStorage<DataContainer> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<DataContainer> capability, DataContainer instance, EnumFacing side) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger("lingqi", instance.getLingQi());
        nbtTagCompound.setBoolean("modified", instance.modified());
        return nbtTagCompound;
    }

    @Override
    public void readNBT(Capability<DataContainer> capability, DataContainer instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound) {
            NBTTagCompound nbtTagCompound = (NBTTagCompound) nbt;
            instance.setLingQi(nbtTagCompound.getInteger("lingqi"));
            instance.Modified(nbtTagCompound.getBoolean("modified"));
        }
    }
}
