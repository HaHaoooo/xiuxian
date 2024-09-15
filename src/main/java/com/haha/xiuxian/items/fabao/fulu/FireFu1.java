package com.haha.xiuxian.items.fabao.fulu;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuBase;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireFu1 extends FuLuBase {

    public FireFu1() {
        super("fire_fu_1",
                100,
                Attributes.FIRE,
                new FuLuInfo("右键使用", "发射出一道明赤炎的异火能量", "消耗100点火灵力值，且只能使用四次", "黄级上品")
        );
        this.setMaxDamage(3);
    }

    @Override
    protected void preActivate(World world, EntityPlayer player, EnumHand hand) {
        if (this.getDamage(player.getHeldItem(hand)) == this.getMaxDamage(player.getHeldItem(hand))){
            player.getHeldItem(hand).shrink(1);
        }
    }

    @Override
    protected void activate(World world, EntityPlayer player, EnumHand hand) {
        this.setDamage(player.getHeldItem(hand), this.getDamage(player.getHeldItem(hand)) + 1);
    }

    @Override
    protected void postActivate(World world, EntityPlayer player, EnumHand hand) {
        double posX = player.posX;
        double posY = player.posY + player.getEyeHeight();
        double posZ = player.posZ;

        double speed = 0.1;
        Vec3d look = player.getLookVec();
        int maxParticles = 130;
        int generated = 0;

        boolean collided = false;
        while (!collided && generated < maxParticles) {
            posX += look.x * speed;
            posY += look.y * speed;
            posZ += look.z * speed;

            double spiralOffset = generated * 0.1;
            double offsetX = Math.cos(spiralOffset) * 0.5;
            double offsetY = Math.sin(spiralOffset) * 0.5;
            double offsetZ = spiralOffset * 0.1;

            double x = posX + offsetX;
            double y = posY + offsetY;
            double z = posZ + offsetZ;

            if (world.isAirBlock(new BlockPos(posX, posY, posZ))) {
                world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
                generated += 1;
            } else {
                collided = true;
            }
        }
        world.createExplosion(null, posX, posY, posZ, 4.0f, false);
    }
}
