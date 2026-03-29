package com.example;

import com.example.client.model.GhostModel;
import com.example.client.render.GhostRenderer;
import com.example.client.render.IronBulletEntityRenderer;
import com.example.client.render.ModModelLayers;
import com.example.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ClientMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register Ghost Model and Renderer
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GHOST, GhostModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GHOST, GhostRenderer::new);

        // Register Iron Bullet Renderer
        EntityRendererRegistry.register(ModEntities.IRON_BULLET, IronBulletEntityRenderer::new);
    }
}
