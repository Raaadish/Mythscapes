package com.radish.mythscapes.datagen.recipe;

import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.tags.MythItemTags;
import mekanism.api.datagen.recipe.builder.CombinerRecipeBuilder;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MythRecipeProvider extends AbstractRecipeProvider {

    public MythRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override  // Weird mapped name lol
    public void buildShapelessRecipes(@NotNull Consumer<IFinishedRecipe> consumer) {
        this.consumer = consumer;

        // Stonecutting
        this.stonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICKS, MythItems.BEJEWELED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICK_SLAB, MythItems.BEJEWELED_GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB, MythItems.BEJEWELED_GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICK_STAIRS, MythItems.BEJEWELED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICK_WALL, MythItems.BEJEWELED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GILDED_GALVITE_BRICKS, MythItems.GILDED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GILDED_GALVITE_BRICK_SLAB, MythItems.GILDED_GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.GILDED_GALVITE_BRICK_VERTICAL_SLAB, MythItems.GILDED_GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.GILDED_GALVITE_BRICK_STAIRS, MythItems.GILDED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GILDED_GALVITE_BRICK_WALL, MythItems.GILDED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POWERED_GALVITE_BRICKS, MythItems.POWERED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POWERED_GALVITE_BRICK_SLAB, MythItems.POWERED_GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POWERED_GALVITE_BRICK_VERTICAL_SLAB, MythItems.POWERED_GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POWERED_GALVITE_BRICK_STAIRS, MythItems.POWERED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POWERED_GALVITE_BRICK_WALL, MythItems.POWERED_GALVITE.get(), 1, consumer);

        this.stonecuttingRecipe(MythItems.GALVITE_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.GALVITE_VERTICAL_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_WALL, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_GALVITE_VERTICAL_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_WALL, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.CHISELED_POLISHED_GALVITE, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_GALVITE_BRICK_VERTICAL_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_WALL, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.GALVITE_SHINGLE_VERTICAL_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_GALVITE_VERTICAL_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_STAIRS, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_WALL, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.CHISELED_POLISHED_GALVITE, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_GALVITE_BRICK_VERTICAL_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_STAIRS, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_WALL, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.GALVITE_SHINGLE_VERTICAL_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_SLAB, MythItems.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_GALVITE_BRICK_VERTICAL_SLAB, MythItems.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_STAIRS, MythItems.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_WALL, MythItems.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.GALVITE_SHINGLE_VERTICAL_SLAB, MythItems.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.GALVITE_SHINGLES.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.GALVITE_SHINGLE_VERTICAL_SLAB, MythItems.GALVITE_SHINGLES.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.GALVITE_SHINGLES.get(), 1, consumer);

        this.stonecuttingRecipe(MythItems.TROLLSTONE_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.TROLLSTONE_VERTICAL_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.TROLLSTONE_STAIRS, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.TROLLSTONE_WALL, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_TROLLSTONE_VERTICAL_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_STAIRS, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_WALL, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_STAIRS, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_WALL, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE, MythItems.TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_SLAB, MythItems.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_TROLLSTONE_VERTICAL_SLAB, MythItems.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_STAIRS, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_WALL, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_SLAB, MythItems.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB, MythItems.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_STAIRS, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_WALL, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_SLAB, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 2, consumer);
        this.compatStonecuttingRecipe(QUARK, MythItems.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 2, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_STAIRS, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_WALL, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.stonecuttingRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);

        // Shaped
        this.shaped(MythItems.POLISHED_GALVITE.get(), 4, MythItems.GALVITE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.GALVITE.get())
                .save(consumer);

        this.shaped(MythItems.POLISHED_GALVITE_BRICKS.get(), 4, MythItems.POLISHED_GALVITE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.POLISHED_GALVITE.get())
                .save(consumer);

        this.shaped(MythItems.CHISELED_POLISHED_GALVITE.get(), 1, MythItems.POLISHED_GALVITE_SLAB.get())
                .pattern("#")
                .pattern("#")
                .define('#', MythItems.POLISHED_GALVITE_SLAB.get())
                .save(consumer);

        this.shaped(MythItems.GALVITE_SHINGLES.get(), 4, MythItems.POLISHED_GALVITE.get())
                .pattern("S#")
                .pattern("#S")
                .define('#', MythItems.POLISHED_GALVITE.get())
                .define('S', MythItems.GALVITE.get())
                .save(consumer);

        this.shaped(MythItems.GILDED_GALVITE_BRICKS.get(), 4, MythItems.GILDED_GALVITE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.GILDED_GALVITE.get())
                .save(consumer);

        this.shaped(MythItems.BEJEWELED_GALVITE_BRICKS.get(), 4, MythItems.BEJEWELED_GALVITE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.BEJEWELED_GALVITE.get())
                .save(consumer);

        this.shaped(MythItems.POWERED_GALVITE_BRICKS.get(), 4, MythItems.POWERED_GALVITE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.POWERED_GALVITE.get())
                .save(consumer);

        this.shaped(MythItems.POLISHED_TROLLSTONE.get(), 4, MythItems.TROLLSTONE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.TROLLSTONE.get())
                .save(consumer);

        this.shaped(MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 4, MythItems.POLISHED_TROLLSTONE.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.POLISHED_TROLLSTONE.get())
                .save(consumer);

        this.shaped(MythItems.POLISHED_TROLLSTONE_PILLAR.get(), 2, MythItems.POLISHED_TROLLSTONE.get())
                .pattern("#")
                .pattern("#")
                .define('#', MythItems.POLISHED_TROLLSTONE.get())
                .save(consumer);

        this.shaped(MythItems.CHISELED_POLISHED_TROLLSTONE.get(), 1, MythItems.POLISHED_TROLLSTONE_SLAB.get())
                .pattern("#")
                .pattern("#")
                .define('#', MythItems.POLISHED_TROLLSTONE_SLAB.get())
                .save(consumer);

        this.shaped(MythItems.WOLT_WOOD.get(), 3, MythItems.WOLT_LOG.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.WOLT_LOG.get())
                .save(consumer);

        this.shaped(MythItems.WOLT_WOOD_STRIPPED.get(), 3, MythItems.WOLT_LOG_STRIPPED.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.WOLT_LOG_STRIPPED.get())
                .save(consumer);

        this.shaped(MythItems.WOLT_DOOR.get(), 3, MythItems.WOLT_PLANKS.get())
                .group("wooden_door")
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.WOLT_PLANKS.get())
                .save(consumer);

        this.shaped(MythItems.WOLT_TRAPDOOR.get(), 2, MythItems.WOLT_PLANKS.get())
                .group("wooden_trapdoor")
                .pattern("###")
                .pattern("###")
                .define('#', MythItems.WOLT_PLANKS.get())
                .save(consumer);

        this.shaped(MythItems.WOLT_POWDER_BLOCK.get(), 1, MythItems.WOLT_POWDER.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', MythItems.WOLT_POWDER.get())
                .save(consumer);

        this.shaped(MythItems.GOLDEN_WOLT_POWDER_BLOCK.get(), 1, MythItems.GOLDEN_WOLT_POWDER.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', MythItems.GOLDEN_WOLT_POWDER.get())
                .save(consumer);

        this.shaped(MythItems.BIOBULB_CLUSTER.get(), 1, MythItems.BIOBULB.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.BIOBULB.get())
                .save(consumer);

        this.shaped(MythItems.ROASTED_BIOBULB_CLUSTER.get(), 1, MythItems.ROASTED_BIOBULB.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.ROASTED_BIOBULB.get())
                .save(consumer);

        this.shaped(MythItems.BIOBULB_LAMP.get(), 1, MythItems.BIOBULB.get())
                .pattern(" # ")
                .pattern("#B#")
                .pattern(" # ")
                .define('#', Tags.Items.DUSTS_REDSTONE)
                .define('B', MythItems.BIOBULB.get())
                .save(consumer);

        this.shaped(MythItems.STATIC_COTTON_BLOCK.get(), 1, MythItems.STATIC_COTTON.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.STATIC_COTTON.get())
                .save(consumer);

        this.shaped(MythItems.STATIC_COTTON_PILES.get(), 6, MythItems.STATIC_COTTON_BLOCK.get())
                .pattern("###")
                .define('#', MythItems.STATIC_COTTON_BLOCK.get())
                .save(consumer);

        this.shaped(MythItems.LAUNCHER_RAIL.get(), 6, MythItems.WOLT_POWDER.get())
                .pattern("S S")
                .pattern("GWG")
                .pattern("SRS")
                .define('S', Tags.Items.RODS_WOODEN)
                .define('G', MythItems.GOLDEN_WOLT_POWDER.get())
                .define('W', MythItems.WOLT_POWDER.get())
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .save(consumer);

        this.shaped(MythItems.SNAIL_SHELL_BLOCK.get(), 1, MythItems.SNAIL_SHELL.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', MythItems.SNAIL_SHELL.get())
                .save(consumer);

        this.shaped(MythItems.SNAIL_SHELL_BRICKS.get(), 4, MythItems.SNAIL_SHELL_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.SNAIL_SHELL_BLOCK.get())
                .save(consumer);

        this.shaped(MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get(), 1, MythItems.BEJEWELED_SNAIL_SHELL.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', MythItems.BEJEWELED_SNAIL_SHELL.get())
                .save(consumer);

        this.shaped(MythItems.BEJEWELED_SNAIL_SHELL_BRICKS.get(), 4, MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .define('#', MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .save(consumer);

        this.shaped(MythItems.GOLDEN_WOLT_FRUIT.get(), 1, MythItems.WOLT_FRUIT.get())
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('P', MythItems.WOLT_FRUIT.get())
                .save(consumer);

        this.shaped(MythItems.GLOWBALL.get(), 1, MythItems.BIOBULB.get())
                .pattern("BBB")
                .pattern("BSG")
                .pattern("GGG")
                .define('B', MythItems.BIOBULB.get())
                .define('S', Items.SNOWBALL)
                .define('G', Tags.Items.DUSTS_GLOWSTONE)
                .save(consumer);

        this.boatRecipe(MythItems.WOLT_BOAT.get(), MythItems.WOLT_PLANKS.get(), consumer);

        // Shapeless
        this.shapelessPlanksRecipe(MythItems.WOLT_PLANKS.get(), MythItemTags.WOLT_LOGS, consumer);

        this.shapeless(MythItems.WOLT_POWDER.get(), 9, MythItems.WOLT_POWDER_BLOCK.get())
                .requires(MythItems.WOLT_POWDER_BLOCK.get())
                .save(consumer, "wolt_powder_from_wolt_powder_block");

        this.singleIngredientRecipe(MythItems.WOLT_POWDER.get(), 1, MythItems.WOLT_FRUIT.get());

        this.shapeless(MythItems.GOLDEN_WOLT_POWDER.get(), 9, MythItems.GOLDEN_WOLT_POWDER_BLOCK.get())
                .requires(MythItems.GOLDEN_WOLT_POWDER_BLOCK.get())
                .save(consumer, "golden_wolt_powder_from_golden_wolt_powder_block");

        this.singleIngredientRecipe(MythItems.GOLDEN_WOLT_POWDER.get(), 1, MythItems.GOLDEN_WOLT_FRUIT.get());

        this.shapeless(MythItems.COTTON_HIDE.get(), 1, MythItems.STATIC_COTTON.get())
                .requires(Items.LEATHER)
                .requires(Items.LEATHER)
                .requires(MythItems.STATIC_COTTON.get())
                .requires(MythItems.STATIC_COTTON.get())
                .save(consumer);

        this.shapeless(MythItems.SNAIL_SHELL.get(), 9, MythItems.SNAIL_SHELL_BLOCK.get())
                .requires(MythItems.SNAIL_SHELL_BLOCK.get())
                .save(consumer, "snail_shell_from_snail_shell_block");

        this.singleIngredientRecipe(Items.PURPLE_DYE, 1, MythItems.SNAIL_SHELL.get());

        this.shapeless(MythItems.BEJEWELED_SNAIL_SHELL.get(), 9, MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .requires(MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .save(consumer, "bejeweled_snail_shell_from_bejeweled_snail_shell_block");

        this.shapeless(MythItems.COTTON_HOOD.get(), 1, MythItems.COTTON_HIDE.get())
                .requires(MythItems.COTTON_HIDE.get())
                .requires(Items.LEATHER_HELMET)
                .save(consumer);

        this.shapeless(MythItems.COTTON_COAT.get(), 1, MythItems.COTTON_HIDE.get())
                .requires(MythItems.COTTON_HIDE.get())
                .requires(Items.LEATHER_CHESTPLATE)
                .save(consumer);

        this.shapeless(MythItems.COTTON_PANTS.get(), 1, MythItems.COTTON_HIDE.get())
                .requires(MythItems.COTTON_HIDE.get())
                .requires(Items.LEATHER_LEGGINGS)
                .save(consumer);

        this.shapeless(MythItems.COTTON_BOOTS.get(), 1, MythItems.COTTON_HIDE.get())
                .requires(MythItems.COTTON_HIDE.get())
                .requires(Items.LEATHER_BOOTS)
                .save(consumer);

        this.slabRecipe(MythBlocks.GALVITE_SLAB.get(), MythBlocks.GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_GALVITE_SLAB.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get(), MythBlocks.POLISHED_GALVITE_BRICKS.get(), consumer);
        this.slabRecipe(MythBlocks.GALVITE_SHINGLE_SLAB.get(), MythBlocks.GALVITE_SHINGLES.get(), consumer);
        this.slabRecipe(MythBlocks.GILDED_GALVITE_BRICK_SLAB.get(), MythBlocks.GILDED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get(), MythBlocks.BEJEWELED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.POWERED_GALVITE_BRICK_SLAB.get(), MythBlocks.POWERED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.TROLLSTONE_SLAB.get(), MythBlocks.TROLLSTONE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_TROLLSTONE_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), consumer);
        this.slabRecipe(MythBlocks.SNAIL_SHELL_BRICK_SLAB.get(), MythBlocks.SNAIL_SHELL_BRICKS.get(), consumer);
        this.slabRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get(), consumer);
        this.woodenSlabRecipe(MythBlocks.WOLT_SLAB.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.stairsRecipe(MythBlocks.GALVITE_STAIRS.get(), MythBlocks.GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_GALVITE_STAIRS.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS.get(), MythBlocks.POLISHED_GALVITE_BRICKS.get(), consumer);
        this.stairsRecipe(MythBlocks.GALVITE_SHINGLE_STAIRS.get(), MythBlocks.GALVITE_SHINGLES.get(), consumer);
        this.stairsRecipe(MythBlocks.GILDED_GALVITE_BRICK_STAIRS.get(), MythBlocks.GILDED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS.get(), MythBlocks.BEJEWELED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.POWERED_GALVITE_BRICK_STAIRS.get(), MythBlocks.POWERED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.TROLLSTONE_STAIRS.get(), MythBlocks.TROLLSTONE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_TROLLSTONE_STAIRS.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS.get(), MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), consumer);
        this.stairsRecipe(MythBlocks.SNAIL_SHELL_BRICK_STAIRS.get(), MythBlocks.SNAIL_SHELL_BRICKS.get(), consumer);
        this.stairsRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_STAIRS.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get(), consumer);
        this.woodenStairsRecipe(MythBlocks.WOLT_STAIRS.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.wallRecipe(MythBlocks.GALVITE_WALL.get(), MythBlocks.GALVITE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_GALVITE_WALL.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_GALVITE_BRICK_WALL.get(), MythBlocks.POLISHED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.GILDED_GALVITE_BRICK_WALL.get(), MythBlocks.GILDED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL.get(), MythBlocks.BEJEWELED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.POWERED_GALVITE_BRICK_WALL.get(), MythBlocks.POWERED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.TROLLSTONE_WALL.get(), MythBlocks.TROLLSTONE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_TROLLSTONE_WALL.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL.get(), MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.SNAIL_SHELL_BRICK_WALL.get(), MythBlocks.SNAIL_SHELL_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_WALL.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get(), consumer);

        this.pressureplateRecipe(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.pressureplateRecipe(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.woodenPressureplateRecipe(MythBlocks.WOLT_PRESSURE_PLATE.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.buttonRecipe(MythBlocks.POLISHED_GALVITE_BUTTON.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.buttonRecipe(MythBlocks.POLISHED_TROLLSTONE_BUTTON.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.woodenButtonRecipe(MythBlocks.WOLT_BUTTON.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.woodenFenceRecipe(MythBlocks.WOLT_FENCE.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.woodenFenceGateRecipe(MythBlocks.WOLT_FENCE_GATE.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        // Smelting
        this.smeltingRecipe(MythBlocks.GILDED_GALVITE.get(), Items.GOLD_INGOT, 1.0F, consumer);
        this.smeltingRecipe(MythBlocks.BEJEWELED_GALVITE.get(), Items.DIAMOND, 1.0F, consumer);
        this.smeltingRecipe(MythBlocks.POWERED_GALVITE.get(), Items.REDSTONE, 0.7F, consumer);
        this.smeltingRecipe(MythBlocks.BIOBULB_CLUSTER.get(), MythBlocks.ROASTED_BIOBULB_CLUSTER.get(), 0.50F, consumer);
        this.smeltingRecipe(MythItems.BIOBULB.get(), MythItems.ROASTED_BIOBULB.get(), 0.35F, consumer);
        this.smeltingRecipe(MythItems.BLISTERBERRY.get(), MythItems.ACTIVATED_BLISTERBERRY.get(), 0.35F, consumer);

        // Smoking
        this.smokerRecipe(MythBlocks.BIOBULB_CLUSTER.get(), MythBlocks.ROASTED_BIOBULB_CLUSTER.get(), 0.50F, consumer);
        this.smokerRecipe(MythItems.BIOBULB.get(), MythItems.ROASTED_BIOBULB.get(), 0.35F, consumer);

        // Campfire cooking
        this.campfireRecipe(MythBlocks.BIOBULB_CLUSTER.get(), MythBlocks.ROASTED_BIOBULB_CLUSTER.get(), 0.50F, consumer);
        this.campfireRecipe(MythItems.BIOBULB.get(), MythItems.ROASTED_BIOBULB.get(), 0.35F, consumer);

        // Blasting
        this.blastingRecipe(MythBlocks.GILDED_GALVITE.get(), Items.GOLD_INGOT, 1.0F, consumer);
        this.blastingRecipe(MythBlocks.BEJEWELED_GALVITE.get(), Items.DIAMOND, 1.0F, consumer);
        this.blastingRecipe(MythBlocks.POWERED_GALVITE.get(), Items.REDSTONE, 0.7F, consumer);

        // Quark compat
        this.quarkVerticalSlabRecipe(MythBlocks.GALVITE_VERTICAL_SLAB.get(), MythBlocks.GALVITE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_GALVITE_VERTICAL_SLAB.get(), MythBlocks.POLISHED_GALVITE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.GALVITE_SHINGLE_VERTICAL_SLAB.get(), MythBlocks.GALVITE_SHINGLE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.GILDED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.GILDED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POWERED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.POWERED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.WOLT_VERTICAL_SLAB.get(), MythBlocks.WOLT_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.TROLLSTONE_VERTICAL_SLAB.get(), MythBlocks.TROLLSTONE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_TROLLSTONE_VERTICAL_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(), MythBlocks.SNAIL_SHELL_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB.get());

        this.quarkVerticalPlanksRecipe(MythBlocks.WOLT_VERTICAL_PLANKS.get(), MythBlocks.WOLT_PLANKS.get());

        this.woodSignRecipe(MythItems.WOLT_SIGN.get(), MythBlocks.WOLT_PLANKS.get());

        this.quarkLeafCarpetRecipe(MythBlocks.WOLT_LEAF_CARPET.get(), MythBlocks.WOLT_LEAVES.get());

        this.quarkWoodenPostRecipe(MythBlocks.WOLT_POST.get(), MythBlocks.WOLT_WOOD.get());
        this.quarkWoodenPostRecipe(MythBlocks.WOLT_POST_STRIPPED.get(), MythBlocks.WOLT_WOOD_STRIPPED.get());

        this.quarkLadderRecipe(MythBlocks.WOLT_LADDER.get(), MythBlocks.WOLT_PLANKS.get());

        this.quarkBookshelfRecipe(MythBlocks.WOLT_BOOKSHELF.get(), MythBlocks.WOLT_PLANKS.get());

        this.quarkChestRecipe(MythBlocks.WOLT_CHEST.get(), MythBlocks.WOLT_TRAPPED_CHEST.get(), MythBlocks.WOLT_PLANKS.get(), MythItemTags.WOLT_LOGS);

        // Mekanism compat
        this.modCompatRecipeNoAdvancement("mekanism", recipeConsumer -> {
            CombinerRecipeBuilder.combining(ItemStackIngredient.deserialize(itemFromName(new ResourceLocation("mekanism", "dust_diamond"), 3)), ItemStackIngredient.from(MythItems.GALVITE.get()), new ItemStack(MythItems.BEJEWELED_GALVITE.get())).build(recipeConsumer);
        });
        this.modCompatRecipeNoAdvancement("mekanism", recipeConsumer -> {
            CombinerRecipeBuilder.combining(ItemStackIngredient.from(Tags.Items.DUSTS_REDSTONE, 12), ItemStackIngredient.from(MythItems.GALVITE.get()), new ItemStack(MythItems.POWERED_GALVITE.get())).build(recipeConsumer);
        });
        this.modCompatRecipeNoAdvancement("mekanism", recipeConsumer -> {
            CombinerRecipeBuilder.combining(ItemStackIngredient.deserialize(itemFromName(new ResourceLocation("mekanism", "dust_gold"), 8)), ItemStackIngredient.from(MythItems.GALVITE.get()), new ItemStack(MythItems.GILDED_GALVITE.get())).build(recipeConsumer);
        });
    }
}
