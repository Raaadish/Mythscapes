package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.Tags;

public class MythBlockTags {

    // Yay, initialization
    public static void init() {
        MythItemTags.init();
        MythEntityTags.init();
        MythFluidTags.init();
    }

    public static final Tags.IOptionalNamedTag<Block> SALT_BLOCKS = forgeTag("salt_blocks");

    public static final Tags.IOptionalNamedTag<Block> VERTICAL_SLAB = modTag("vertical_slab", "quark");
    public static final Tags.IOptionalNamedTag<Block> PLANKS_VERTICAL_SLAB = modTag("planks_vertical_slab", "quark");
    public static final Tags.IOptionalNamedTag<Block> LADDERS = modTag("ladders", "quark");
    public static final Tags.IOptionalNamedTag<Block> HEDGES = modTag("hedges", "quark");


    private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
        return BlockTags.createOptional(new ResourceLocation("forge", name));
    }

    private static ITag.INamedTag<Block> mythTag(String name) {
        return BlockTags.bind(Mythscapes.resourceLoc(name).toString());
    }

    private static Tags.IOptionalNamedTag<Block> modTag(String name, String modid) {
        return BlockTags.createOptional(new ResourceLocation(modid, name));
    }
}
