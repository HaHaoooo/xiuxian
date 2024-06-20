package com.haha.xiuxian.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ZhenFaEntity1 extends EntityLiving {

    public ZhenFaEntity1(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.1F);
    }
}
