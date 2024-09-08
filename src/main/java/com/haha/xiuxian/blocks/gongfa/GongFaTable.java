package com.haha.xiuxian.blocks.gongfa;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.gui.GuiHandler;
import com.haha.xiuxian.gui.gongfa.table.GongFaTableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.Objects;

@Mod.EventBusSubscriber
public class GongFaTable extends Block {
    public static GongFaTable INSTANCE = new GongFaTable();

    public GongFaTable() {
        super(Material.ANVIL, MapColor.AIR);
        this.setRegistryName(XiuXian.MODID, "gongfa_table");
        this.setUnlocalizedName(XiuXian.MODID + ".gongfa_table");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_BLOCK);
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerTileEntity(GongFaTableTileEntity.class, new ResourceLocation(XiuXian.MODID, "gongfa_table"));
    }

    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(INSTANCE), 0, new ModelResourceLocation(Objects.requireNonNull(Item.getItemFromBlock(INSTANCE).getRegistryName()), "inventory"));
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
