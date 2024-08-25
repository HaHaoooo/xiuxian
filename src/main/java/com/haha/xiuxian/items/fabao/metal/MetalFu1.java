package com.haha.xiuxian.items.fabao.metal;

import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.util.gui.ToolTipHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class MetalFu1 extends Item {

    public static MetalFu1 INSTANCE = new MetalFu1();

    public MetalFu1() {
        this.setRegistryName("xiuxian:metal_fu_1");
        this.setUnlocalizedName("xiuxian.metal_fu_1");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_FABAO);
        this.setMaxStackSize(1);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation("xiuxian:fu_lu", "inventory"));
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add("使用： 右键使用");
            tooltip.add("作用： 生成1~8个随机数量的金锭");
            tooltip.add("消耗： 消耗70点金灵力值");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("§l§k黄级中品");
            ToolTipHelper.ChangeMetal();
        } else {
            tooltip.add("按下shift查看更多信息");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (DataInject.DataContainer.getMetal() >= 70) {
            if (!worldIn.isRemote) {
                DataInject.DataContainer.setMetal(DataInject.DataContainer.getMetal() - 70);
                playerIn.getHeldItem(handIn).shrink(1);
                playerIn.dropItem(Items.GOLD_INGOT, new Random().nextInt(7) + 1);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
