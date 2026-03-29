package com.example.entity.projectile;

import com.example.ModItems;
import com.example.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.entity.FlyingItemEntity;

public class IronBulletEntity extends PersistentProjectileEntity implements FlyingItemEntity {

    public IronBulletEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setDamage(8.0); // High base damage for the early game
    }

    public IronBulletEntity(World world, LivingEntity owner) {
        super(ModEntities.IRON_BULLET, owner, world);
        this.setDamage(8.0);
    }

    public IronBulletEntity(World world, double x, double y, double z) {
        super(ModEntities.IRON_BULLET, x, y, z, world);
        this.setDamage(8.0);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.IRON_BULLET);
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(ModItems.IRON_BULLET);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient && hitResult.getType() == HitResult.Type.BLOCK) {
            this.discard(); // Bullets break on block impact
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true; // Fast bullets don't arc significantly
    }
}
