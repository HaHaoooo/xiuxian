package com.haha.xiuxian.items.Crops;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
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

@Mod.EventBusSubscriber
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

    @SubscribeEvent
    public void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Objects.requireNonNull(Item.getItemFromBlock(this).getRegistryName()), "inventory"));
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