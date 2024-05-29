package com.haha.xiuxian.items.FaBao;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.gui.ToolTipHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class JumpFuLu extends Item {
    public static JumpFuLu INSTANCE = new JumpFuLu();

    public JumpFuLu(){
        this.setRegistryName("xiuxian:double_jump_fu_lu");
        this.setUnlocalizedName("xiuxian.double_jump_fu_lu");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_FABAO);
        this.setMaxStackSize(1);
        this.setMaxDamage(50);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation("xiuxian:fu_lu", "inventory"));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        playerIn.setJumping(true);
        playerIn.motionY = 0.5;
        if (!worldIn.isRemote) {
            if (this.getDamage(playerIn.getHeldItem(handIn)) == this.getMaxDamage(playerIn.getHeldItem(handIn))) {
                playerIn.getHeldItem(handIn).shrink(1);
            } else {
                playerIn.fallDistance = 0;
                this.setDamage(playerIn.getHeldItem(handIn), this.getDamage(playerIn.getHeldItem(handIn)) + 1);
            }
        }
        for (int i = 0; i < 20; i++) {
            double offsetX = worldIn.rand.nextGaussian() * 0.05;
            double offsetY = worldIn.rand.nextGaussian() * 0.05;
            double offsetZ = worldIn.rand.nextGaussian() * 0.05;
            worldIn.spawnParticle(EnumParticleTypes.CLOUD, playerIn.posX, playerIn.posY + 0.2, playerIn.posZ, offsetX, offsetY, offsetZ);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if(GuiScreen.isShiftKeyDown()){
            tooltip.add("使用： 右键使用");
            tooltip.add("作用： 能在空中腾空一瞬，但速度一般");
            tooltip.add("消耗： 消耗1点耐久度");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("§l§k黄级下品");
            ToolTipHelper.ChangeEmpty();
        } else {
            tooltip.add("按下shift查看更多信息");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
