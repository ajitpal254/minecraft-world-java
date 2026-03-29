package com.example.client.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import com.example.entity.projectile.IronBulletEntity;

public class IronBulletEntityRenderer extends FlyingItemEntityRenderer<IronBulletEntity> {
    public IronBulletEntityRenderer(EntityRendererFactory.Context context) {
        super(context, 1.0f, true); // scale, render full bright
    }
}
