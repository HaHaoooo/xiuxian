package com.haha.xiuxian.blocks.gongfa;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.creativetabs.XiuXianCreativeTabs;
import com.haha.xiuxian.entity.tileentities.GongFaTableTileEntity;
import com.haha.xiuxian.gui.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber
public class GongFaTable extends Block{

    public static GongFaTable INSTANCE = new GongFaTable();

    public GongFaTable() {
        super(Material.ANVIL, MapColor.AIR);
        this.setRegistryName(XiuXian.MODID, "gongfa_table");
        this.setUnlocalizedName(XiuXian.MODID + ".gongfa_table");
        this.setCreativeTab(XiuXianCreativeTabs.XIUXIAN_BLOCK);
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerTileEntity(GongFaTableTileEntity.class, new ResourceLocation(XiuXian.MODID, "gongfa_table"));
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new GongFaTableTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(XiuXian.INSTANCE, GuiHandler.GUI_GONGFA_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
