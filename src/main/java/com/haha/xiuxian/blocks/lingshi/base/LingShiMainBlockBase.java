package com.haha.xiuxian.blocks.lingshi.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import javax.annotation.Nonnull;
import java.util.Random;

public class LingShiMainBlockBase extends Block {
    public LingShiMainBlockBase(String level) {
        super(Material.ROCK, MapColor.STONE);
        this.setSoundType(SoundType.STONE);
        this.setHardness(2.5F);
        this.setHarvestLevel("pickaxe", 2);
        this.setRegistryName("xiuxian:" + level + "_ling_shi_main_block");
        this.setUnlocalizedName("xiuxian." + level + "_ling_shi_main_block");
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return 1;
    }
}
