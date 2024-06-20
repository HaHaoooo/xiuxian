package com.haha.xiuxian.blocks.lingshi.Low;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.items.lingshi.LowLingShi;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class LowLingShiOre1 extends Block {


    public static Block INSTANCE = new LowLingShiOre1();


    public LowLingShiOre1() {
        super(Material.ROCK, MapColor.STONE);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(3.5F);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_BLOCK);
        this.setRegistryName("xiuxian:low_ling_shi_ore_1");
        this.setUnlocalizedName("xiuxian.low_ling_shi_ore_1");
        this.setLightLevel(0.3F);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Objects.requireNonNull(Item.getItemFromBlock(this).getRegistryName()), "inventory"));
    }

    @Nonnull
    @Override
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        return LowLingShi.INSTANCE;
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return 1;
    }


    @Override
    public boolean addDestroyEffects(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull ParticleManager manager) {
        return destroyEffect(world, pos);
    }

    public static boolean destroyEffect(@Nonnull World world, @Nonnull BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;
        for (int i = 0; i < 100; i++) {
            world.spawnParticle(EnumParticleTypes.END_ROD, x, y, z, 0, 0.1, 0, 1);
        }
        return true;
    }

    @Override
    public boolean canPlaceBlockOnSide(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        return worldIn.getBlockState(pos.down(1)).getBlock() == LowLingShiMainBlock.INSTANCE;
    }
    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        return new AxisAlignedBB(0.2, 0, 0.2, 0.8, 0.5, 0.8);
    }
}
