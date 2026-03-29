package com.example.world;

import com.example.Main;
import com.example.world.feature.CrystalSpikeFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

/**
 * ModWorldgen
 * -----------
 * Central class for all world generation in this mod.
 *
 * WHAT THIS DOES:
 * 1. Registers the custom CRYSTAL_SPIKE feature type with Minecraft's registry
 * 2. Injects "custom_ore" placed feature into every overworld biome →
 * underground ore veins
 * 3. Injects "crystal_spikes" placed feature into every overworld biome →
 * surface spike towers
 *
 * HOW TO ADD MORE FEATURES:
 * - Create the configured_feature + placed_feature JSON in
 * data/my_app/worldgen/
 * - Add a BiomeModifications.addFeature() call below pointing to your
 * placed_feature key
 *
 * HOW TO LIMIT TO SPECIFIC BIOMES (instead of all overworld):
 * Replace BiomeSelectors.foundInOverworld() with:
 * BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST)
 * or a tag-based selector.
 */
public class ModWorldgen {

    // Register the custom Feature type so Minecraft knows how to deserialize it
    // from JSON
    public static final Feature<DefaultFeatureConfig> CRYSTAL_SPIKE = Registry.register(
            Registries.FEATURE,
            new Identifier(Main.MOD_ID, "crystal_spike"),
            new CrystalSpikeFeature(DefaultFeatureConfig.CODEC));

    public static void register() {
        Main.LOGGER.info("Registering world generation for {}", Main.MOD_ID);

        // ── Underground ore veins ────────────────────────────────────────────────
        // Config: data/my_app/worldgen/configured_feature/custom_ore.json (size 15
        // vein)
        // Placed: data/my_app/worldgen/placed_feature/custom_ore.json (20 per chunk, y
        // -64..64)
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                        new Identifier(Main.MOD_ID, "custom_ore")));

        // ── Surface crystal spike towers ─────────────────────────────────────────
        // Config: data/my_app/worldgen/configured_feature/crystal_spikes.json
        // Placed: data/my_app/worldgen/placed_feature/crystal_spikes.json (1 in 12
        // chunks)
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                        new Identifier(Main.MOD_ID, "crystal_spikes")));
    }
}
