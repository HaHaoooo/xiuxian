package com.haha.xiuxian.registries;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.entity.ZhenFaEntity1;
import com.haha.xiuxian.entity.renderer.ZhenFaEntity1Renderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class EntityRegistry {

    public static final List<EntityEntry> ENTITY_ENTRIES = new ArrayList<>();

   public static final Map<Class<? extends Entity>, IRenderFactory<? extends Entity>> ENTITY_RENDERERS = new HashMap<>();

    public static void init() {
        registerEntity(ZhenFaEntity1.class, new ZhenFaEntity1Renderer.Factory(), "zhen_fa_entity_1", 1000);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().registerAll(ENTITY_ENTRIES.toArray(new EntityEntry[0]));
    }

    private static void registerEntity(Class<? extends Entity> clazz, IRenderFactory<? extends Entity> factory, String name, int id){
        ENTITY_ENTRIES.add(EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(XiuXian.MODID, name), id).name("xiuxian." + name).tracker(64, 10, true).build());
        ENTITY_RENDERERS.put(clazz, factory);
    }
}