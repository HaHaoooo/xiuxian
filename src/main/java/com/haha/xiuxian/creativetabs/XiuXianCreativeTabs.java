package com.haha.xiuxian.creativetabs;

import com.haha.xiuxian.blocks.lingshi.LingShiOre;
import com.haha.xiuxian.blocks.zhenfa.ZhenYan;
import com.haha.xiuxian.items.LingGenTest;
import com.haha.xiuxian.items.gongfa.batch.EnumEmptyGongFa;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;

public class XiuXianCreativeTabs {

    public static final CreativeTabs XIUXIAN_BLOCK = new CreativeTabs("xiuxian.block") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(LingShiOre.Low.MainBlock);
        }

        @Override
        public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> items) {
            NonNullList<ItemStack> ores = NonNullList.create();
            ores.addAll(Arrays.asList(
                    new ItemStack(LingShiOre.Low.MainBlock),
                    new ItemStack(LingShiOre.Low.Ore1),
                    new ItemStack(LingShiOre.Low.Ore2),
                    new ItemStack(LingShiOre.Low.Ore3),
                    new ItemStack(LingShiOre.Low.Ore4),
                    new ItemStack(LingShiOre.Mid.MainBlock),
                    new ItemStack(LingShiOre.Mid.Ore1),
                    new ItemStack(LingShiOre.Mid.Ore2),
                    new ItemStack(LingShiOre.Mid.Ore3),
                    new ItemStack(LingShiOre.Mid.Ore4),
                    new ItemStack(LingShiOre.High.MainBlock),
                    new ItemStack(LingShiOre.High.Ore1),
                    new ItemStack(LingShiOre.High.Ore2),
                    new ItemStack(LingShiOre.High.Ore3),
                    new ItemStack(LingShiOre.High.Ore4),
                    new ItemStack(LingShiOre.Extreme.MainBlock),
                    new ItemStack(LingShiOre.Extreme.Ore1),
                    new ItemStack(LingShiOre.Extreme.Ore2),
                    new ItemStack(LingShiOre.Extreme.Ore3),
                    new ItemStack(LingShiOre.Extreme.Ore4)
            ));

            for (int i = 0; i < ores.size(); i++) {
                if (i > 0 && i % 5 == 0) {
                    items.addAll(Collections.nCopies(4, ItemStack.EMPTY));
                }
                items.add(ores.get(i));
            }
            items.addAll(Collections.nCopies(4, ItemStack.EMPTY));
            super.displayAllRelevantItems(items);
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
