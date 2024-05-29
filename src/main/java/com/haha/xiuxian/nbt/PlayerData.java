package com.haha.xiuxian.nbt;

import com.haha.xiuxian.capabilities.PlayerData.Storage.DataContainer;
import com.haha.xiuxian.capabilities.PlayerData.Attach.DataInject;
import com.haha.xiuxian.worldgen.InitialHouse;
import com.haha.xiuxian.XiuXian;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;
import java.util.Random;

import static com.haha.xiuxian.config.MainConfig.*;

@Mod.EventBusSubscriber(modid = XiuXian.MODID)
public class PlayerData {

    private static final DataContainer container = DataInject.DataContainer;
    @SubscribeEvent
    public void Limit(TickEvent.PlayerTickEvent event) {
        if (container != null) {
            if (container.getLingLi() >= container.getLingLiMax()) {
                container.setLingLi(container.getLingLiMax());
            }
            if (container.getMetal() >= container.getMetalMax()) {
                container.setMetal(container.getMetalMax());
            }
            if (container.getWood() >= container.getWoodMax()) {
                container.setWood(container.getWoodMax());
            }
            if (container.getWater() >= container.getWaterMax()) {
                container.setWater(container.getWaterMax());
            }
            if (container.getFire() >= container.getFireMax()) {
                container.setFire(container.getFireMax());
            }
            if (container.getDirt() >= container.getDirtMax()) {
                container.setDirt(container.getDirtMax());
            }
        }
    }


    @SubscribeEvent
    public void LoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        NBTTagCompound persistence = event.player.getEntityData();
        if (!persistence.hasKey("initialized")) {
            int count = 0;
            Item guideBook = Objects.requireNonNull(Item.getByNameOrId("patchouli:guide_book"));
            NBTTagCompound bookNbt = new NBTTagCompound();
            bookNbt.setString("patchouli:book", "xiuxian:xiuxian");
            ItemStack stack = new ItemStack(guideBook);
            stack.setTagCompound(bookNbt);
            event.player.addItemStackToInventory(stack);

            InitialHouse.initialHouse.generate(event.player.world, new Random(), new BlockPos(event.player.posX, event.player.posY, event.player.posZ));

            if (container != null) {
                if (Metal) {
                    count++;
                    container.setMetal(0);
                    container.setMetalMax(100);
                }
                if (Wood) {
                    count++;
                    container.setWood(0);
                    container.setWoodMax(100);
                }
                if (Water) {
                    count++;
                    container.setWater(0);
                    container.setWaterMax(100);
                }
                if (Fire) {
                    count++;
                    container.setFire(0);
                    container.setFireMax(100);
                }
                if (Dirt) {
                    count++;
                    container.setDirt(0);
                    container.setDirtMax(100);
                }
                container.setLingLi(0);
                container.setLingLiMax(100 * count);
                container.showGui(false);
                container.setLevel("凡人");
            }
            persistence.setBoolean("initialized", true);
        }
    }


    @SubscribeEvent
    public void EntityRespawn(PlayerEvent.PlayerRespawnEvent event) {
        NBTTagCompound persistence = event.player.getEntityData();
        int count = 0;
        if (container != null) {
            if (Metal) {
                count++;
                container.setMetal(0);
                container.setMetalMax(100);
            }
            if (Wood) {
                count++;
                container.setWood(0);
                container.setWoodMax(100);
            }
            if (Water) {
                count++;
                container.setWater(0);
                container.setWaterMax(100);
            }
            if (Fire) {
                count++;
                container.setFire(0);
                container.setFireMax(100);
            }
            if (Dirt) {
                count++;
                container.setDirt(0);
                container.setDirtMax(100);
            }
            container.setLingLi(0);
            container.setLingLiMax(100 * count);
        }
        persistence.setBoolean("initialized", true);
    }
}
