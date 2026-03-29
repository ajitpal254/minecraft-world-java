package com.example.client.model;

import com.example.entity.custom.GhostEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

public class GhostModel<T extends GhostEntity> extends SinglePartEntityModel<T> {
    private final ModelPart root;

    public GhostModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        
        root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -24.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        root.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -16.0F, -2.0F, 8.0F, 12.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        root.addChild("arms", ModelPartBuilder.create().uv(40, 16).cuboid(-8.0F, -16.0F, -2.0F, 16.0F, 12.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root.getChild("head").yaw = headYaw * 0.017453292F;
        this.root.getChild("head").pitch = headPitch * 0.017453292F;
        
        float floatAnim = (float) Math.sin(animationProgress * 0.1F) * 2.0F; // Exaggerate float
        this.root.getChild("body").pivotY = floatAnim;
        this.root.getChild("head").pivotY = floatAnim;
        this.root.getChild("arms").pivotY = floatAnim;
        this.root.getChild("arms").pitch = -0.5F; // Arms up menacingly
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
