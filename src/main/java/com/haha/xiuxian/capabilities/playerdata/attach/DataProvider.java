package com.haha.xiuxian.capabilities.playerdata.attach;

import com.haha.xiuxian.capabilities.playerdata.storage.DataContainer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.haha.xiuxian.capabilities.playerdata.storage.DataStorage.capaSide;

@Mod.EventBusSubscriber
public class DataProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(DataContainer.class)
    public static final Capability<DataContainer> XIUXIAN_CAP = null;


    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return true;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        assert false;
        return XIUXIAN_CAP.cast(DataInject.DataContainer);
    }

    @Override
    public NBTBase serializeNBT() {
        assert false;
        return XIUXIAN_CAP.getStorage().writeNBT(XIUXIAN_CAP, DataInject.DataContainer, capaSide);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        assert false;
        XIUXIAN_CAP.getStorage().readNBT(XIUXIAN_CAP, DataInject.DataContainer, capaSide, nbt);
    }
}
