package com.providence.heartless.datagen;

import com.providence.heartless.Heartless;
import com.providence.heartless.util.ModTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {


    public ModItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, provider, blockTagProvider, Heartless.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTag.Items.HEART)
                .add(Heartless.CLERIC_HEART.get(),
                        Heartless.BARBARIAN_HEART.get(),
                        Heartless.RANGER_HEART.get(),
                        Heartless.ROGUE_HEART.get(),
                        Heartless.QUEEN_HEART.get(),
                        Heartless.VOID_HEART.get(),
                        Heartless.MOMENTUM_HEART.get());
        this.tag(ModTag.Items.ENEMY_HEART)
                .add(Heartless.CLERIC_HEART.get(),
                        Heartless.BARBARIAN_HEART.get(),
                        Heartless.RANGER_HEART.get(),
                        Heartless.ROGUE_HEART.get());
    }
}
