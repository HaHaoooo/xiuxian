package com.haha.xiuxian.capabilities.playerdata;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DataInject{
    public static IDataContainer DataContainer = new DataContainerImpl();

    @CapabilityInject(IDataContainer.class)
    public static final Capability<IDataContainer> XIUXIAN_CAP = null;

    public static final EnumFacing capaSide = EnumFacing.UP;

    public static void register() {
        CapabilityManager.INSTANCE.register(IDataContainer.class, new Capability.IStorage<IDataContainer>() {
            @Override
            public NBTBase writeNBT(Capability<IDataContainer> capability, IDataContainer instance, EnumFacing side) {
                if (side == capaSide) {
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
                    tagCompound.setString("level", instance.getLevel());
                    return tagCompound;
                } else {
                    return new NBTTagCompound();
                }
            }

            @Override
            public void readNBT(Capability<IDataContainer> capability, IDataContainer instance, EnumFacing side, NBTBase nbt) {
                if (side == capaSide) {
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
                        instance.setLevel(tagCompound.getString("level"));
                    }
                }
            }
        }, DataContainerImpl::new);
    }
}