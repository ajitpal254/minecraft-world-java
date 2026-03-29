package com.example.client.render;

import com.example.Main;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer GHOST = new EntityModelLayer(new Identifier(Main.MOD_ID, "ghost"), "main");
}
