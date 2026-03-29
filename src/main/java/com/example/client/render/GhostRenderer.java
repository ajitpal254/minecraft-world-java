package com.example.client.render;

import com.example.Main;
import com.example.client.model.GhostModel;
import com.example.entity.custom.GhostEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GhostRenderer extends MobEntityRenderer<GhostEntity, GhostModel<GhostEntity>> {
    
    private static final Identifier TEXTURE = new Identifier(Main.MOD_ID, "textures/entity/ghost.png");

    public GhostRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostModel<>(context.getPart(ModModelLayers.GHOST)), 0.5f);
    }

    @Override
    public Identifier getTexture(GhostEntity entity) {
        return TEXTURE;
    }

    @Override
    protected RenderLayer getRenderLayer(GhostEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(getTexture(entity)); // Ghost makes it translucent
    }
}
