package com.example;

import com.example.world.ModWorldgen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final String MOD_ID = "my_app";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        com.example.entity.ModEntities.registerModEntities();
        
        // Register entity attributes
        net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register(com.example.entity.ModEntities.GHOST, com.example.entity.custom.GhostEntity.createGhostAttributes());

        ModWorldgen.register();
        LOGGER.info("Hello from {}! World generation active.", MOD_ID);
    }
}
