package com.haha.xiuxian.creativetabs;

import com.haha.xiuxian.blocks.lingshi.Low.LowLingShiMainBlock;
import com.haha.xiuxian.blocks.zhenfa.ZhenYan;
import com.haha.xiuxian.items.LingGenTest;
import com.haha.xiuxian.items.gongfa.batch.EnumEmptyGongFa;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class XiuXian_CreativeTabs {
    public static final CreativeTabs XIUXIAN_BLOCK = new CreativeTabs("xiuxian.block") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(LowLingShiMainBlock.INSTANCE);
        }
    };

    public static final CreativeTabs XIUXIAN_FABAO = new CreativeTabs("xiuxian.fabao") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.WOODEN_SWORD);
        }
    };

    public static final CreativeTabs XIUXIAN_GONGFA = new CreativeTabs("xiuxian.gongfa") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(EnumEmptyGongFa.TEMPLATE.getItem());
        }
    };

    public static final CreativeTabs XIUXIAN_ITEM = new CreativeTabs("xiuxian.item") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(LingGenTest.INSTANCE);
        }
    };

    public static final CreativeTabs XIUXIAN_ZHENFA = new CreativeTabs("xiuxian.zhenfa") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ZhenYan.INSTANCE);
        }
    };
}
