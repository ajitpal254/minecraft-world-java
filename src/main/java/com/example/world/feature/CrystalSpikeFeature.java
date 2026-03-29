package com.example.world.feature;

import com.example.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * CrystalSpikeFeature
 * -------------------
 * Generates a cluster of tall crystal pillars made of CUSTOM_BLOCK on the
 * surface.
 * Each cluster has 3–6 spikes, each 8–18 blocks tall, within a 4-block radius.
 *
 * HOW TO TWEAK:
 * - More spikes per cluster: change nextBetween(3, 6) → e.g. nextBetween(5, 10)
 * - Taller spikes: change nextBetween(8, 18) → e.g. nextBetween(15, 30)
 * - Wider cluster spread: change nextBetween(-4, 4) → e.g. nextBetween(-8, 8)
 * - Different block: swap ModBlocks.CUSTOM_BLOCK for any other registered block
 */
public class CrystalSpikeFeature extends Feature<DefaultFeatureConfig> {

    public CrystalSpikeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        // Number of individual spikes in this cluster
        int spikeCount = random.nextBetween(3, 6);

        for (int s = 0; s < spikeCount; s++) {
            // Each spike is offset slightly from the cluster origin
            int dx = random.nextBetween(-4, 4);
            int dz = random.nextBetween(-4, 4);

            // Start from the top surface (ignores leaves/flowers)
            BlockPos base = world.getTopPosition(
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    origin.add(dx, 0, dz));

            int height = random.nextBetween(8, 18);

            for (int i = 0; i < height; i++) {
                BlockPos target = base.up(i);
                // Only place in air so we don't overwrite existing structures
                if (world.isAir(target)) {
                    world.setBlockState(target, ModBlocks.CUSTOM_BLOCK.getDefaultState(), 3);
                }
            }
        }

        return true;
    }
}
