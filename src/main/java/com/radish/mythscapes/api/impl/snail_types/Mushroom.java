package com.radish.mythscapes.api.impl.snail_types;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;

import java.util.List;

public class Mushroom implements ISnailType {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/pygmy_snail/mushroom.png");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("mushroom");

    @Override
    public ResourceLocation getSnailTexture() {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getName() {
        return NAME;
    }

    @Override
    public List<ResourceLocation> getSpawnBiomes() {
        return ImmutableList.of(
                Biomes.MUSHROOM_FIELDS.getLocation(),
                Biomes.MUSHROOM_FIELD_SHORE.getLocation()
        );
    }
}
