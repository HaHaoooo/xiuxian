package com.haha.xiuxian.blocks.lingshi.base;

import com.haha.xiuxian.blocks.lingshi.LingShiOre;
import com.haha.xiuxian.items.lingshi.ExtremeLingShi;
import com.haha.xiuxian.items.lingshi.HighLingShi;
import com.haha.xiuxian.items.lingshi.LowLingShi;
import com.haha.xiuxian.items.lingshi.MidLingShi;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class LingShiOreBase extends Block {

    private final int grade;
    private final String level;

    public LingShiOreBase(String level, int grade) {
        super(Material.ROCK, MapColor.STONE);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(3.5F);
        this.setHarvestLevel("pickaxe", 2);
        this.setRegistryName("xiuxian:" + level + "_ling_shi_ore_" + grade);
        this.setUnlocalizedName("xiuxian." + level + "_ling_shi_ore_" + grade);
        this.setLightLevel(0.3F);
        this.grade = grade;
        this.level = level;
    }

    @Nonnull
    @Override
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        switch (level){
            case "low":
                return LowLingShi.INSTANCE;
            case "mid":
                return MidLingShi.INSTANCE;
            case "high":
                return HighLingShi.INSTANCE;
            case "extreme":
                return ExtremeLingShi.INSTANCE;
            default:
                return super.getItemDropped(state, rand, fortune);
        }
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return random.nextInt(grade * 2) + 1;
    }


    @Override
    public boolean addDestroyEffects(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull ParticleManager manager) {
        return destroyEffect(world, pos);
    }

    public boolean destroyEffect(@Nonnull World world, @Nonnull BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;
        for (int i = 0; i < 100; i++) {
            world.spawnParticle(EnumParticleTypes.END_ROD, x, y, z, 0, (double) grade / 10, 0, 1);
        }
        return true;
    }

    @Override
    public boolean canPlaceBlockOnSide(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        switch (level) {
            case "low":
                return worldIn.getBlockState(pos.down(1)).getBlock().equals(LingShiOre.Low.MainBlock);
            case "mid":
                return worldIn.getBlockState(pos.down(1)).getBlock().equals(LingShiOre.Mid.MainBlock);
            case "high":
                return worldIn.getBlockState(pos.down(1)).getBlock().equals(LingShiOre.High.MainBlock);
            case "extreme":
                return worldIn.getBlockState(pos.down(1)).getBlock().equals(LingShiOre.Extreme.MainBlock);
            default:
                return super.canPlaceBlockOnSide(worldIn, pos, side);
        }
    }
    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        switch (grade){
            case 1:
                return new AxisAlignedBB(0.2, 0, 0.2, 0.8, 0.5, 0.8);
            case 2:
                return new AxisAlignedBB(0.2, 0, 0.2, 0.8, 0.6, 0.8);
            case 3:
                return new AxisAlignedBB(0.2, 0, 0.2, 0.8, 0.8, 0.8);
            default:
                return super.getBoundingBox(state, source, pos);
        }
    }
}
