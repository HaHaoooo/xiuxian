package com.haha.xiuxian.blocks.ZhenFa;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ZhenYan extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);


    public static ZhenYan INSTANCE = new ZhenYan();
    public ZhenYan() {
        super(Material.ROCK);
        this.setRegistryName("xiuxian:zhen_yan");
        this.setUnlocalizedName("xiuxian.zhen_yan");
        this.setHarvestLevel("pickaxe", 1);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_ZHENFA);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Objects.requireNonNull(Item.getItemFromBlock(this).getRegistryName()), "inventory"));
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        switch (state.getValue(FACING)) {
            case EAST:
            case WEST:
            case SOUTH:
            case NORTH:
            default:
                return new AxisAlignedBB(-0.5, 0, -0.5, 1.5, 0.5, 1.5);
        }
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public void onBlockPlacedBy(World world, @Nonnull BlockPos pos, IBlockState state, EntityLivingBase placer, @Nonnull ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
}