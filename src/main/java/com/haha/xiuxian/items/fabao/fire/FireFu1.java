package com.haha.xiuxian.items.fabao.fire;

import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.util.gui.ToolTipHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class FireFu1 extends Item {

    public static FireFu1 INSTANCE = new FireFu1();

    public FireFu1() {
        this.setRegistryName("xiuxian:fire_fu_1");
        this.setUnlocalizedName("xiuxian.fire_fu_1");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_FABAO);
        this.setMaxStackSize(1);
        this.setMaxDamage(3);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation("xiuxian:fu_lu", "inventory"));
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add("使用： 右键使用");
            tooltip.add("作用： 发射出一道明赤炎的异火能量");
            tooltip.add("消耗： 消耗100点火灵力值，且只能使用四次");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("§l§k黄级上品");
            ToolTipHelper.ChangeFire();
        } else {
            tooltip.add("按下shift查看更多信息");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (this.getDamage(playerIn.getHeldItem(handIn)) == this.getMaxDamage(playerIn.getHeldItem(handIn))){
            playerIn.getHeldItem(handIn).shrink(1);
        }

        if (DataInject.DataContainer.getFire() >= 100) {
            if (!worldIn.isRemote) {
                DataInject.DataContainer.setFire(DataInject.DataContainer.getFire() - 100);
                this.setDamage(playerIn.getHeldItem(handIn), this.getDamage(playerIn.getHeldItem(handIn)) + 1);
            }
            double posX = playerIn.posX;
            double posY = playerIn.posY + playerIn.getEyeHeight();
            double posZ = playerIn.posZ;

            double speed = 0.1;
            Vec3d look = playerIn.getLookVec();
            int maxParticles = 130;
            int generated = 0;

            boolean collided = false;
            while (!collided && generated < maxParticles) {
                posX += look.x * speed;
                posY += look.y * speed;
                posZ += look.z * speed;

                double spiralOffset = generated * 0.1;
                double offsetX = Math.cos(spiralOffset) * 0.5;
                double offsetY = Math.sin(spiralOffset) * 0.5;
                double offsetZ = spiralOffset * 0.1;

                double x = posX + offsetX;
                double y = posY + offsetY;
                double z = posZ + offsetZ;

                if (worldIn.isAirBlock(new BlockPos(posX, posY, posZ))) {
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
                    generated += 1;
                } else {
                    collided = true;
                }
            }
            worldIn.createExplosion(null, posX, posY, posZ, 4.0f, false);
        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentString("火属性灵力值不够"));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
