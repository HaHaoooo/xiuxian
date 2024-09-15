package com.haha.xiuxian.items.fabao.fulu;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuBase;
import com.haha.xiuxian.items.fabao.fulu.base.FuLuInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.Random;

public class MetalFu1 extends FuLuBase {

    public MetalFu1() {
        super("metal_fu_1",
                70,
                Attributes.METAL,
                new FuLuInfo("右键使用", "生成1~8个随机数量的金锭", "消耗70点金灵力值", "黄级中品")
        );
    }

    @Override
    protected void preActivate(World world, EntityPlayer player, EnumHand hand) {

    }

    @Override
    protected void activate(World world, EntityPlayer player, EnumHand hand) {
        player.getHeldItem(hand).shrink(1);
        player.dropItem(Items.GOLD_INGOT, new Random().nextInt(7) + 1);
    }

    @Override
    protected void postActivate(World world, EntityPlayer player, EnumHand hand) {

    }
}
