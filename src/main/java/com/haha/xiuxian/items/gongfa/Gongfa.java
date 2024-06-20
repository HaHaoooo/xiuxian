package com.haha.xiuxian.items.gongfa;

import com.haha.xiuxian.creativetabs.XiuXian_CreativeTabs;
import com.haha.xiuxian.gui.gongfashow.GongFaInventory;
import com.haha.xiuxian.items.PutInInventoryHelper;
import com.haha.xiuxian.items.gongfa.gui.GongFaInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
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

    public Gongfa() {
        this.setRegistryName("xiuxian:gongfa");
        this.setUnlocalizedName("xiuxian.gongfa");
        this.setCreativeTab(XiuXian_CreativeTabs.XIUXIAN_GONGFA);
        this.setMaxStackSize(1);
    }

    @SubscribeEvent
    public static void IModel(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (GuiScreen.isShiftKeyDown()) {
            if (!worldIn.isRemote) {
                Minecraft.getMinecraft().displayGuiScreen(new GongFaInfo(
                        "旻天疾威，敷于下土。谋犹回遹，何日斯沮？谋臧不从，不臧覆用。我视谋犹，亦孔之邛。潝潝訿訿，亦孔之哀。谋之其臧，则具是违。谋之不臧，则具是依。我视谋犹，伊于胡厎。",
                        10,
                        "天道酬勤",
                        30,
                        new double[]{50, 80, 57, 90, 23, 34}));
            }
        } else {
            ItemStack heldItem = playerIn.getHeldItem(handIn);
            PutInInventoryHelper.putInInventory(worldIn, GongFaInventory.instance, heldItem, "空");
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
