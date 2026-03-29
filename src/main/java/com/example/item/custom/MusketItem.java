package com.example.item.custom;

import com.example.ModItems;
import com.example.entity.projectile.IronBulletEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MusketItem extends Item {

    public MusketItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        
        // Ensure the player has the required bullet in their inventory (or is in creative mode).
        boolean hasAmmo = user.getAbilities().creativeMode || user.getInventory().contains(new ItemStack(ModItems.IRON_BULLET));

        if (!hasAmmo) {
            return TypedActionResult.fail(itemStack);
        }

        if (!world.isClient) {
            IronBulletEntity bulletEntity = new IronBulletEntity(world, user);
            
            // Speed of 3.0F, divergence of 1.0F
            bulletEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.0F);

            world.spawnEntity(bulletEntity);

            // Deduct ammo if not in creative mode
            if (!user.getAbilities().creativeMode) {
                user.getInventory().removeOne(new ItemStack(ModItems.IRON_BULLET));
            }

            // Damage the musket item slowly over time
            itemStack.damage(1, user, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
        }

        // Apply a cooldown for balance (e.g. 2 seconds = 40 ticks)
        user.getItemCooldownManager().set(this, 40);

        // Sound effect
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.5F, 1.0F);
        
        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
