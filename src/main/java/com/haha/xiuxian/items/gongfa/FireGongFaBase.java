package com.haha.xiuxian.items.gongfa;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.gui.gongfashow.GongFaInventory;
import com.haha.xiuxian.items.gongfa.gui.GongFaInfo;
import com.haha.xiuxian.util.gui.inventory.PutInInventoryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public class FireGongFaBase extends Item {

    private final String fileName;

    public FireGongFaBase(String registryName) {
        this.setRegistryName("xiuxian:" + registryName);
        this.setUnlocalizedName("xiuxian." + registryName);
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_GONGFA);
        this.setMaxStackSize(1);
        this.fileName = registryName + ".json";
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (GuiScreen.isShiftKeyDown()) {
            if (!worldIn.isRemote) {
                try {
                    Minecraft.getMinecraft().displayGuiScreen(new GongFaInfo("fire/" + fileName, 30));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            ItemStack heldItem = playerIn.getHeldItem(handIn);
            PutInInventoryHelper.putInInventory(worldIn, GongFaInventory.instance, heldItem, "火");
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.add("右键装备功法");
        tooltip.add("Shift+右键查看详细信息");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
