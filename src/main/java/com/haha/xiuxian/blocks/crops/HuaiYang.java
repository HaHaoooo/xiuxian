package com.haha.xiuxian.blocks.crops;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;

public class HuaiYang extends BlockFlower {

    public static HuaiYang INSTANCE = new HuaiYang();

    public HuaiYang() {
        this.setSoundType(SoundType.PLANT);
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_ITEM);
        this.setRegistryName("xiuxian:huai_yang_cao");
        this.setUnlocalizedName("xiuxian.huai_yang_cao");
        this.setHardness(0F);
        this.setResistance(0F);
        this.setLightLevel(2F);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune) {
        drops.add(new ItemStack(this));
    }

    @Nonnull
    @Override
    public EnumFlowerColor getBlockType() {
        return EnumFlowerColor.YELLOW;
    }


    @Override
    public boolean canPlaceBlockAt(@Nonnull World worldIn, @Nonnull BlockPos pos) {
        BlockPos blockpos = pos.down();
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        return block instanceof BlockStone;
    }
}