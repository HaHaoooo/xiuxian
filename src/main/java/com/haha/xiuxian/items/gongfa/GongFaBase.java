package com.haha.xiuxian.items.gongfa;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.creativetabs.XiuXianCreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GongFaBase extends Item {
    private final Attributes attribute;
    public GongFaBase(String registryName, Attributes attribute){
        this.setRegistryName("xiuxian:" + registryName);
        this.setUnlocalizedName("xiuxian." + registryName);
        this.setCreativeTab(XiuXianCreativeTabs.XIUXIAN_GONGFA);
        this.setMaxStackSize(1);
        this.attribute = attribute;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.add("这是本" + attribute.getChinese() + "属性功法");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
