package com.haha.xiuxian.items;

import com.haha.xiuxian.creativetabs.XiuXianCreativeTabs;
import com.haha.xiuxian.nbt.XiuXianWorldData;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.haha.xiuxian.config.MainConfig.*;

@Mod.EventBusSubscriber
public class LingGenTest extends Item {
    private static boolean isParticleShowing = false;
    private static int particleTimer = 0;
    private static int circleTimer = 0;

    public static final Item INSTANCE = new LingGenTest();

    public LingGenTest() {
        setRegistryName("xiuxian:ling_gen_test");
        setUnlocalizedName("xiuxian.ling_gen_test");
        setCreativeTab(XiuXianCreativeTabs.XIUXIAN_ITEM);
        setMaxStackSize(1);
    }

    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(INSTANCE, 0, new ModelResourceLocation(Objects.requireNonNull(INSTANCE.getRegistryName()), "inventory"));
    }

    @SubscribeEvent
    public static void onPlayerTick(net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent event) {
        if (isParticleShowing) {
            if (particleTimer > 0) {
                EntityPlayer player = event.player;
                spawnEndRodParticle(player);
                circleTimer += 5;
                particleTimer--;
            } else {
                isParticleShowing = false;
            }
        }
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        StringBuilder lingGenName = new StringBuilder();

        if (isEmpty()) {
            lingGenName.append("§f§l空灵根");
        }
        if (Metal) {
            lingGenName.append("§6金");
        }
        if (Wood) {
            lingGenName.append("§3木");
        }
        if (Water) {
            lingGenName.append("§1水");
        }
        if (Fire) {
            lingGenName.append("§c火");
        }
        if (Dirt) {
            lingGenName.append("§e土");
        }

        if (!worldIn.isRemote) {
            playerIn.sendMessage(new TextComponentString("您的灵根是: " + lingGenName));
            enableParticleEffects();
            XiuXianWorldData data = new XiuXianWorldData("persistence", worldIn);
            NBTTagCompound compound = data.get();
            if (!compound.getBoolean("showLingLiEx")) {
                compound.setBoolean("showLingLiEx", true);
                data.write(compound);
            }
        }
        playerIn.getHeldItem(handIn).shrink(1);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void enableParticleEffects() {
        isParticleShowing = true;
        particleTimer = 100;
        circleTimer = 0;
    }


    public static boolean isEmpty() {
        if (Empty) {
            Metal = Wood = Water = Fire = Dirt = false;
        }
        return Empty;
    }

    public static void spawnEndRodParticle(EntityPlayer player) {
        double x = player.posX;
        double y = player.posY + 1.2;
        double z = player.posZ;

        double circleOffsetX = Math.sin(Math.toRadians(circleTimer)) * 1;
        double circleOffsetZ = Math.cos(Math.toRadians(circleTimer)) * 1;

        player.world.spawnParticle(EnumParticleTypes.END_ROD, x + circleOffsetX, y, z + circleOffsetZ, 0, 0, 0);
    }
}