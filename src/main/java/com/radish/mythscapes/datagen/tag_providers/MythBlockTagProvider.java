package com.radish.mythscapes.datagen.tag_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.tags.MythBlockTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythBlockTagProvider extends BlockTagsProvider {

    public MythBlockTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(Tags.Blocks.ORES_GOLD).add(
                MythBlocks.GILDED_GALVITE.get()
        );

        this.getOrCreateBuilder(Tags.Blocks.ORES_DIAMOND).add(
                MythBlocks.BEJEWELED_GALVITE.get()
        );

        this.getOrCreateBuilder(Tags.Blocks.ORES_REDSTONE).add(
                MythBlocks.POWERED_GALVITE.get()
        );

        this.getOrCreateBuilder(Tags.Blocks.COBBLESTONE).add(
                MythBlocks.GALVITE.get(),
                MythBlocks.TROLLSTONE.get()
        );

        this.getOrCreateBuilder(BlockTags.PLANKS).add(
                MythBlocks.WOLT_PLANKS.get(),
                MythBlocks.WOLT_VERTICAL_PLANKS.get()
        );

        this.getOrCreateBuilder(BlockTags.SLABS).add(
                MythBlocks.GALVITE_SLAB.get(),
                MythBlocks.POLISHED_GALVITE_SLAB.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.GILDED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.POWERED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.TROLLSTONE_SLAB.get(),
                MythBlocks.POLISHED_TROLLSTONE_SLAB.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get(),
                MythBlocks.SNAIL_SHELL_BRICK_SLAB.get(),
                MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(
                MythBlocks.WOLT_SLAB.get()
                //MythBlocks.VIRIDIAN_SLAB.get()
        );

        this.getOrCreateBuilder(BlockTags.STAIRS).add(
                MythBlocks.GALVITE_STAIRS.get(),
                MythBlocks.POLISHED_GALVITE_STAIRS.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.GILDED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.POWERED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.TROLLSTONE_STAIRS.get(),
                MythBlocks.POLISHED_TROLLSTONE_STAIRS.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS.get(),
                MythBlocks.SNAIL_SHELL_BRICK_STAIRS.get(),
                MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_STAIRS.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(
                MythBlocks.WOLT_STAIRS.get()
                //MythBlocks.VIRIDIAN_STAIRS.get()
        );

        this.getOrCreateBuilder(BlockTags.WALLS).add(
                MythBlocks.GALVITE_WALL.get(),
                MythBlocks.POLISHED_GALVITE_WALL.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_WALL.get(),
                MythBlocks.GILDED_GALVITE_BRICK_WALL.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_WALL.get(),
                MythBlocks.POWERED_GALVITE_BRICK_WALL.get(),
                MythBlocks.TROLLSTONE_WALL.get(),
                MythBlocks.POLISHED_TROLLSTONE_WALL.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL.get(),
                MythBlocks.SNAIL_SHELL_BRICK_WALL.get(),
                MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_WALL.get()
        );

        this.getOrCreateBuilder(BlockTags.BUTTONS).add(
                MythBlocks.POLISHED_GALVITE_BUTTON.get(),
                MythBlocks.POLISHED_TROLLSTONE_BUTTON.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(
                MythBlocks.WOLT_BUTTON.get()
                //MythBlocks.VIRIDIAN_BUTTON.get()
        );

        this.getOrCreateBuilder(BlockTags.PRESSURE_PLATES).add(
                MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE.get(),
                MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(
                MythBlocks.WOLT_PRESSURE_PLATE.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(
                MythBlocks.WOLT_FENCE.get()
        );

        this.getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN).add(
                MythBlocks.WOLT_FENCE.get()
        );

        this.getOrCreateBuilder(BlockTags.FENCE_GATES).add(
                MythBlocks.WOLT_FENCE_GATE.get()
        );

        this.getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(
                MythBlocks.WOLT_FENCE_GATE.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(
                MythBlocks.WOLT_DOOR.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_TRAPDOORS).add(
                MythBlocks.WOLT_TRAPDOOR.get()
        );

        this.getOrCreateBuilder(BlockTags.SMALL_FLOWERS).add(
                MythBlocks.CHARGED_DANDELION.get()
        );

        this.getOrCreateBuilder(BlockTags.LEAVES).add(
                MythBlocks.WOLT_LEAVES.get()
        );

        this.getOrCreateBuilder(BlockTags.SAPLINGS).add(
                MythBlocks.WOLT_SAPLING.get()
        );

        this.getOrCreateBuilder(BlockTags.LOGS_THAT_BURN).add(
                MythBlocks.WOLT_LOG.get(),
                MythBlocks.WOLT_LOG_STRIPPED.get(),
                MythBlocks.WOLT_WOOD.get(),
                MythBlocks.WOLT_WOOD_STRIPPED.get()
        );

        this.getOrCreateBuilder(BlockTags.RAILS).add(
                MythBlocks.LAUNCHER_RAIL.get()
        );

        this.getOrCreateBuilder(MythBlockTags.SALT_BLOCKS).addOptional(
                new ResourceLocation("mekanism", "block_salt"));

        this.getOrCreateBuilder(MythBlockTags.VERTICAL_SLAB).add(
                MythBlocks.GALVITE_VERTICAL_SLAB.get(),
                MythBlocks.POLISHED_GALVITE_VERTICAL_SLAB.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_VERTICAL_SLAB.get(),
                MythBlocks.GALVITE_SHINGLE_VERTICAL_SLAB.get(),
                MythBlocks.GILDED_GALVITE_BRICK_VERTICAL_SLAB.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB.get(),
                MythBlocks.POWERED_GALVITE_BRICK_VERTICAL_SLAB.get(),
                MythBlocks.TROLLSTONE_VERTICAL_SLAB.get(),
                MythBlocks.POLISHED_TROLLSTONE_VERTICAL_SLAB.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB.get(),
                MythBlocks.SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(),
                MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB.get()
        );

        this.getOrCreateBuilder(MythBlockTags.PLANKS_VERTICAL_SLAB).add(
                MythBlocks.WOLT_VERTICAL_SLAB.get()
        );

        this.getOrCreateBuilder(MythBlockTags.LADDERS)
                .add(MythBlocks.WOLT_LADDER.get()
        );

        this.getOrCreateBuilder(MythBlockTags.HEDGES)
                .add(MythBlocks.WOLT_HEDGE.get()
        );
    }
}
