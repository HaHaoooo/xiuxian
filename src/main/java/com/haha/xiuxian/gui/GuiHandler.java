package com.haha.xiuxian.gui;


import com.haha.xiuxian.gui.gongfa.table.GongFaTableContainer;
import com.haha.xiuxian.gui.gongfa.table.GongFaTableGui;
import com.haha.xiuxian.entity.tileentities.GongFaTableTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_GONGFA_TABLE = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_GONGFA_TABLE) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
            if (tileEntity instanceof GongFaTableTileEntity) {
                return new GongFaTableContainer(player.inventory, tileEntity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_GONGFA_TABLE) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
            if (tileEntity instanceof GongFaTableTileEntity) {
                return new GongFaTableGui(player.inventory, (GongFaTableTileEntity) tileEntity);
            }
        }
        return null;
    }
}