package com.haha.xiuxian.items.lingshi;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.haha.xiuxian.config.MainConfig.*;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class HighLingShi extends Item {
    public static final HighLingShi INSTANCE = new HighLingShi();

    public static String[] attrs = {"§6金", "§3木", "§1水", "§c火", "§e土"};

    public HighLingShi(){
        setUnlocalizedName("xiuxian.high_ling_shi");
        setRegistryName("xiuxian:high_ling_shi");
        setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_ITEM);
        setHasSubtypes(true);
        setMaxDamage(0);
        setNoRepair();
    }

    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && nbt.hasKey("lingli")) {
            tooltip.add("灵气值: " + nbt.getInteger("lingli"));
        } else {
            tooltip.add("灵气值: ???");
            tooltip.add("右键探测灵石");
        }
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        NBTTagCompound nbt = heldItem.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            int lingqi = new Random().nextInt(100) + 100;
            String name = attrs[new Random().nextInt(5)];
            nbt.setInteger("lingli", lingqi);
            nbt.setString("name", name);
            ItemStack newItem = new ItemStack(heldItem.getItem(), 1, heldItem.getMetadata());
            newItem.setTagCompound(nbt);
            newItem.setStackDisplayName(this.getItemStackDisplayName(heldItem) + "【" + name + "§c】");
            if (!playerIn.inventory.addItemStackToInventory(newItem)) {
                playerIn.dropItem(newItem, false);
            }
            heldItem.shrink(1);
        } else {
            boolean hasLingGen = false;
            if (Metal && nbt.getString("name").equals(attrs[0])){
                addLingLi.addMetal(nbt.getDouble("lingli"), playerIn, worldIn, heldItem);
                hasLingGen = true;
            }
            if (Wood && nbt.getString("name").equals(attrs[1])){
                addLingLi.addWood(nbt.getDouble("lingli"), playerIn, worldIn, heldItem);
                hasLingGen = true;
            }
            if (Water && nbt.getString("name").equals(attrs[2])){
                addLingLi.addWater(nbt.getDouble("lingli"), playerIn, worldIn, heldItem);
                hasLingGen = true;
            }

            if (Fire && nbt.getString("name").equals(attrs[3])){
                addLingLi.addFire(nbt.getDouble("lingli"), playerIn, worldIn, heldItem);
                hasLingGen = true;
            }

            if (Dirt && nbt.getString("name").equals(attrs[4])){
                addLingLi.addDirt(nbt.getDouble("lingli"), playerIn, worldIn, heldItem);
                hasLingGen = true;
            }

            if (!hasLingGen){
                addLingLi.sendPlayerMessage(playerIn, "没有此灵根，无法吸收", worldIn);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ItemStack stack = new ItemStack(this, 1, 0);
            items.add(stack);
        }
    }
}
