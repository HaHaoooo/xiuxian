package com.haha.xiuxian.capabilities.chunk;

import com.haha.xiuxian.XiuXian;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

@Mod.EventBusSubscriber
public class DataAttach {
    @CapabilityInject(DataContainer.class)
    public static final Capability<DataContainer> LINGQI_CAP = null;

    @SubscribeEvent
    public static void register(AttachCapabilitiesEvent<Chunk> event) {
        DataContainer data = new DataContainerImpl();
        if (new Random().nextInt(30) == 1) {
            event.addCapability(new ResourceLocation(XiuXian.MODID, "lingqi"), new ICapabilityProvider() {
                @Override
                public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                    return true;
                }

                @Nullable
                @Override
                public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                    return LINGQI_CAP.cast(data);
                }
            });
        }
    }
}
