package com.haha.xiuxian.capabilities.playerdata;

import com.haha.xiuxian.XiuXian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.haha.xiuxian.capabilities.playerdata.DataInject.XIUXIAN_CAP;
import static com.haha.xiuxian.capabilities.playerdata.DataInject.capaSide;


@Mod.EventBusSubscriber
public class DataAttach {

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(new ResourceLocation(XiuXian.MODID, "data"), new ICapabilitySerializable<NBTBase>() {
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

            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                return true;
            }

            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                assert false;
                return XIUXIAN_CAP.cast(DataInject.DataContainer);
            }
        });
    }
}
