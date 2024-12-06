package com.haha.xiuxian.items.fabao.fulu.base;

import com.haha.xiuxian.Attributes;
import com.haha.xiuxian.capabilities.playerdata.DataInject;
import com.haha.xiuxian.creativetabs.XiuXianCreativeTabs;
import com.haha.xiuxian.util.gui.ToolTipHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.reflections.Reflections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class FuLuBase extends Item {

    private final int lingLiCost;
    private final Attributes element;
    private final FuLuInfo info;
    public static final Set<Class<? extends FuLuBase>> fuLuClasses = new Reflections("com.haha.xiuxian.items.fabao.fulu").getSubTypesOf(FuLuBase.class);

    public FuLuBase(String name, int lingLiCost, Attributes element, FuLuInfo info) {
        this.setRegistryName("xiuxian:" + name);
        this.setUnlocalizedName("xiuxian." + name);
        this.setCreativeTab(XiuXianCreativeTabs.XIUXIAN_FABAO);
        this.setMaxStackSize(1);
        this.lingLiCost = lingLiCost;
        this.element = element;
        this.info = info;
    }

    // 子类必须实现该方法，用于定义符箓的具体作用
    protected abstract void preActivate(World world, EntityPlayer player, EnumHand hand);

    protected abstract void activate(World world, EntityPlayer player, EnumHand hand);

    protected abstract void postActivate(World world, EntityPlayer player, EnumHand hand);

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        preActivate(worldIn, playerIn, handIn);
        double currentLingLi = getElementalLingLi();
        if (currentLingLi >= lingLiCost) {
            if (!worldIn.isRemote) {
                setElementalLingLi(currentLingLi - lingLiCost);
                activate(worldIn, playerIn, handIn);
            }
            postActivate(worldIn, playerIn, handIn);
        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentString(element.getChinese() + "属性灵力值不够"));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add("使用： " + info.usage);
            tooltip.add("作用： " + info.effect);
            tooltip.add("消耗： " + info.consumption);
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("");
            tooltip.add("§l§k" + info.rank);
            ToolTipHelper.ChangeTooltipType(element);
        } else {
            tooltip.add("按下shift查看更多信息");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    // 获取对应元素的灵力值
    private double getElementalLingLi() {
        switch (element) {
            case METAL:
                return DataInject.DataContainer.getMetal();
            case WOOD:
                return DataInject.DataContainer.getWood();
            case WATER:
                return DataInject.DataContainer.getWater();
            case FIRE:
                return DataInject.DataContainer.getFire();
            case DIRT:
                return DataInject.DataContainer.getDirt();
            default:
                return 0;
        }
    }

    // 设置对应元素的灵力值
    private void setElementalLingLi(double value) {
        switch (element) {
            case METAL:
                DataInject.DataContainer.setMetal(value);
                break;
            case WOOD:
                DataInject.DataContainer.setWood(value);
                break;
            case WATER:
                DataInject.DataContainer.setWater(value);
                break;
            case FIRE:
                DataInject.DataContainer.setFire(value);
                break;
            case DIRT:
                DataInject.DataContainer.setDirt(value);
                break;
        }
    }
}