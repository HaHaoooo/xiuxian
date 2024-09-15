package com.haha.xiuxian.items.fabao.fulu;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuBase;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EmptyFu1 extends FuLuBase {

    public EmptyFu1() {
        super("empty_fu_1",
                0,
                Attributes.EMPTY,
                new FuLuInfo("右键使用", "能在空中腾空一瞬，但速度一般", "消耗1点耐久度", "黄级下品")
        );
        this.setMaxDamage(50);
    }

    @Override
    protected void preActivate(World world, EntityPlayer player, EnumHand hand) {
        player.setJumping(true);
        player.motionY = 0.5;
    }

    @Override
    protected void activate(World world, EntityPlayer player, EnumHand hand) {
        if (this.getDamage(player.getHeldItem(hand)) == this.getMaxDamage(player.getHeldItem(hand))) {
            player.getHeldItem(hand).shrink(1);
        } else {
            player.fallDistance = 0;
            this.setDamage(player.getHeldItem(hand), this.getDamage(player.getHeldItem(hand)) + 1);
        }
    }

    @Override
    protected void postActivate(World world, EntityPlayer player, EnumHand hand) {
        for (int i = 0; i < 20; i++) {
            double offsetX = world.rand.nextGaussian() * 0.05;
            double offsetY = world.rand.nextGaussian() * 0.05;
            double offsetZ = world.rand.nextGaussian() * 0.05;
            world.spawnParticle(EnumParticleTypes.CLOUD, player.posX, player.posY + 0.2, player.posZ, offsetX, offsetY, offsetZ);
        }
    }
}
