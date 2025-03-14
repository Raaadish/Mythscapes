package com.radish.mythscapes.common.misc;

import com.radish.mythscapes.common.entities.projectile.GlowballEntity;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class DispenserBehavior {

    public static void register() {
        DispenserBlock.registerBehavior(MythItems.ACTIVATED_BLISTERBERRY.get(), createSimple(MythEntities.BLISTERBERRY));
        DispenserBlock.registerBehavior(MythItems.STATIC_COTTON.get(), createSimple(MythEntities.STATIC_COTTON));

        DispenserBlock.registerBehavior(MythItems.GLOWBALL.get(), new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectile(World worldIn, IPosition position, ItemStack stackIn) {
                return new GlowballEntity(position.x(), position.y(), position.z(), worldIn);
            }
        });
    }

    private static <T extends ProjectileEntity> ProjectileDispenseBehavior createSimple(Supplier<EntityType<T>> entityTypeSupplier) {
        return new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectile(World worldIn, IPosition position, ItemStack stackIn) {
                ProjectileEntity entity = entityTypeSupplier.get().create(worldIn);

                if (entity == null) {
                    entity = new SnowballEntity(worldIn, position.x(), position.y(), position.z());
                }
                entity.setPos(position.x(), position.y(), position.z());
                entity.setDeltaMovement(1.0f, 1.0f, 1.0f);
                return entity;
            }
        };
    }
}
