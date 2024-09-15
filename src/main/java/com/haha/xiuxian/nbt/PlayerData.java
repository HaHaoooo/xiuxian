package com.haha.xiuxian.nbt;

import com.haha.xiuxian.capabilities.playerdata.attach.DataInject;
import com.haha.xiuxian.capabilities.playerdata.storage.DataContainer;
import com.haha.xiuxian.nbt.infoblocks.InfoBlockBoolean;
import com.haha.xiuxian.nbt.infoblocks.InfoBlockCompound;
import com.haha.xiuxian.worldgen.InitialHouse;
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
import java.util.function.DoubleConsumer;

import static com.haha.xiuxian.config.MainConfig.*;

@Mod.EventBusSubscriber
public class PlayerData {

    private static final DataContainer container = DataInject.DataContainer;

    private static void checkAndLimit(double current, double max, DoubleConsumer setResource) {
        if (current >= max) {
            setResource.accept(max);
        }
    }

    @SubscribeEvent
    public static void Limit(TickEvent.PlayerTickEvent event) {
        if (container != null) {
            checkAndLimit(container.getLingLi(), container.getLingLiMax(), container::setLingLi);
            checkAndLimit(container.getMetal(), container.getMetalMax(), container::setMetal);
            checkAndLimit(container.getWood(), container.getWoodMax(), container::setWood);
            checkAndLimit(container.getWater(), container.getWaterMax(), container::setWater);
            checkAndLimit(container.getFire(), container.getFireMax(), container::setFire);
            checkAndLimit(container.getDirt(), container.getDirtMax(), container::setDirt);
        }
    }

    @SubscribeEvent
    public static void LoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        XiuXianWorldData worldData = new XiuXianWorldData("persistence", event.player.world);
        InfoBlockCompound persistence = worldData.get();
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
                containerInitialized(count);
                container.showGui(false);
                container.setLevel("凡人");
            }
            InfoBlockBoolean initialized = new InfoBlockBoolean(true);
            persistence.put("initialized", initialized);
            worldData.write(persistence);
        }
    }

    @SubscribeEvent
    public static void EntityRespawn(PlayerEvent.PlayerRespawnEvent event) {
        XiuXianWorldData worldData = new XiuXianWorldData("persistence", event.player.world);
        InfoBlockCompound persistence = worldData.get();
        int count = 0;
        if (container != null) {
            containerInitialized(count);
        }
        InfoBlockBoolean initialized = new InfoBlockBoolean(true);
        persistence.put("initialized", initialized);
    }

    private static void containerInitialized(int count) {
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
}
