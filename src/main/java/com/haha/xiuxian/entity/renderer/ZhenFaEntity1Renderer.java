package com.haha.xiuxian.entity.renderer;

import com.haha.xiuxian.XiuXian;
import com.haha.xiuxian.entity.ZhenFaEntity1;
import com.haha.xiuxian.entity.models.ZhenFaEntity1Model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ZhenFaEntity1Renderer extends RenderLiving<ZhenFaEntity1> {

    public ZhenFaEntity1Renderer(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull ZhenFaEntity1 entity) {
        return new ResourceLocation(XiuXian.MODID, "textures/entity/zhen_fa_entity_1.png");
    }

    public static class Factory implements IRenderFactory<ZhenFaEntity1>{
        @Override
        public Render<? super ZhenFaEntity1> createRenderFor(RenderManager manager) {
            return new ZhenFaEntity1Renderer(manager, new ZhenFaEntity1Model(), 0F);
        }
    }
}
