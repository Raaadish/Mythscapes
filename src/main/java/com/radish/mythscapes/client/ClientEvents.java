package com.radish.mythscapes.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEffects;
import com.radish.mythscapes.common.tags.MythFluidTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Supplier;

public class ClientEvents {

    private final Supplier<Minecraft> mc = Minecraft::getInstance;

    private static final Vector3d SULFUR_COLORS = new Vector3d(2.2d, 1.85d, 0.01d);

    private static final ResourceLocation PETRIFIED_HEARTS = Mythscapes.resourceLoc("textures/misc/petrified_hearts.png");
    private static final ResourceLocation PETRIFIED_ENTITY = Mythscapes.resourceLoc("textures/misc/petrified.png");

    // Change fog density depending on fluid
    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        ClientPlayerEntity playerEntity = mc.get().player;

        if (playerEntity.isEyeInFluid(MythFluidTags.SULFUR)) {
            event.setDensity(0.5f);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onFogColor(EntityViewRenderEvent.FogColors event) {
        ClientPlayerEntity playerEntity = mc.get().player;

        if (playerEntity.isEyeInFluid(MythFluidTags.SULFUR)) {
            setFogColors(event, SULFUR_COLORS);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onHeartsRender(RenderGameOverlayEvent.Pre event) {
        ClientPlayerEntity playerEntity = mc.get().player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if (playerEntity.hasEffect(MythEffects.PETRIFIED.get())) {
                mc.get().getTextureManager().bind(PETRIFIED_HEARTS);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onRenderLiving(RenderLivingEvent.Pre<?, ?> event) {
        if (event.getEntity().hasEffect(MythEffects.PETRIFIED.get())) {
            Minecraft.getInstance().getTextureManager().bind(PETRIFIED_ENTITY);
        }
    }

    private static void setFogColors(EntityViewRenderEvent.FogColors event, Vector3d colors) {
        event.setRed((float) colors.x());
        event.setGreen((float) colors.y());
        event.setBlue((float) colors.z());
    }
}
