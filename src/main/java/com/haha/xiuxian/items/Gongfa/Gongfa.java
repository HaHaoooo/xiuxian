package com.haha.xiuxian.items.Gongfa;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.gui.GongfaShow.GongFaInventory;
import com.haha.xiuxian.items.PutInInventoryHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
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
import java.util.Objects;


@Mod.EventBusSubscriber
public class Gongfa extends Item {

    public static Gongfa INSTANCE = new Gongfa();

    public Gongfa(){
        this.setRegistryName("xiuxian:gongfa");
        this.setUnlocalizedName("xiuxian.gongfa");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_GONGFA);
        this.setMaxStackSize(1);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        PutInInventoryHelper.putInInventory(worldIn, GongFaInventory.instance, heldItem, "空");
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
