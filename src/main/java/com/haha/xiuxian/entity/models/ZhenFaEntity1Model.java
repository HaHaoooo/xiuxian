package com.haha.xiuxian.entity.models;
// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ZhenFaEntity1Model extends ModelBase {
	private final ModelRenderer bb_main;

	public ZhenFaEntity1Model() {
		textureWidth = 48;
		textureHeight = 48;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, -48, 0, -24.0F, 0F, -24.0F, 48, 1, 48, 0.0F, false));
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.bb_main.rotateAngleY = f2 / 20.f;
	}
}