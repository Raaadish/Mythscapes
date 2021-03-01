package com.radish.mythscapes.api.impl.snail_types;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;

import java.util.List;

public class Jungle implements ISnailType {

    private static final ResourceLocation TEXTURE = TextureUtil.entityTexture("pygmy_snail/jungle");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("jungle");

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
                Biomes.JUNGLE.getLocation(),
                Biomes.JUNGLE_EDGE.getLocation(),
                Biomes.JUNGLE_HILLS.getLocation(),
                Biomes.MODIFIED_JUNGLE.getLocation(),
                Biomes.MODIFIED_JUNGLE_EDGE.getLocation());
    }
}
