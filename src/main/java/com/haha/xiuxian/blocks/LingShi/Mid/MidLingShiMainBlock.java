package com.haha.xiuxian.blocks.LingShi.Mid;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.XiuXian;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class MidLingShiMainBlock extends Block {
    public static Block INSTANCE = new MidLingShiMainBlock();

    public MidLingShiMainBlock() {
        super(Material.ROCK, MapColor.STONE);
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_BLOCK);
        this.setRegistryName("xiuxian:mid_ling_shi_main_block");
        this.setUnlocalizedName("xiuxian.mid_ling_shi_main_block");
        this.setSoundType(SoundType.STONE);
        this.setHardness(2.5F);
        this.setHarvestLevel("pickaxe", 2);
        MinecraftForge.EVENT_BUS.register(MidLingShiMainBlock.class);
    }

    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(INSTANCE), 0, new ModelResourceLocation(Objects.requireNonNull(Item.getItemFromBlock(INSTANCE).getRegistryName()), "inventory"));
    }
    @Nonnull
    @Override
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        return super.getItemDropped(state, rand, fortune);
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return 1;
    }
}
