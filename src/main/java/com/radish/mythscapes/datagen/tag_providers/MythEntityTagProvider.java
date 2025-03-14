package com.radish.mythscapes.datagen.tag_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.tags.MythEntityTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythEntityTagProvider extends EntityTypeTagsProvider {

    public MythEntityTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(MythEntityTags.DIES_IN_SULFUR).add(
                EntityType.GUARDIAN,
                EntityType.DOLPHIN,
                EntityType.SQUID,
                EntityType.COD,
                EntityType.SALMON,
                EntityType.PUFFERFISH,
                EntityType.TROPICAL_FISH,
                MythEntities.POND_SERPENT.get()
        );
        this.tag(MythEntityTags.ELECTRIC).add(
                // This is just here for now so that the tag generates
                EntityType.ENDER_DRAGON
        );
        this.tag(MythEntityTags.LION_PREY).add(
                MythEntities.DEER.get(),
                EntityType.COW,
                EntityType.SHEEP,
                EntityType.PIG,
                EntityType.CHICKEN,
                EntityType.RABBIT,
                EntityType.FOX
        );

        this.tag(MythEntityTags.SWOOSH_WHITELIST).add(
                EntityType.ARMOR_STAND,
                EntityType.ITEM,
                EntityType.BOAT
        );
    }
}
