package com.example.entity;

import com.example.Main;
import com.example.entity.custom.GhostEntity;
import com.example.entity.projectile.IronBulletEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<GhostEntity> GHOST = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "ghost"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GhostEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());

    public static final EntityType<IronBulletEntity> IRON_BULLET = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "iron_bullet"),
            FabricEntityTypeBuilder.<IronBulletEntity>create(SpawnGroup.MISC, IronBulletEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static void registerModEntities() {
        Main.LOGGER.info("Registering Entities for " + Main.MOD_ID);
    }
}
