package com.providence.heartless.datagen;

import com.providence.heartless.Heartless;
import com.providence.heartless.util.ModTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275204_, CompletableFuture<HolderLookup.Provider> p_275194_, CompletableFuture<TagLookup<Item>> p_275207_, CompletableFuture<TagLookup<Block>> p_275634_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275204_, p_275194_, p_275207_, p_275634_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTag.Items.HEART)
                .add(Heartless.CLERIC_HEART.get(),
                        Heartless.BARBARIAN_HEART.get(),
                        Heartless.RANGER_HEART.get(),
                        Heartless.ROGUE_HEART.get(),
                        Heartless.QUEEN_HEART.get());
        this.tag(ModTag.Items.ENEMY_HEART)
                .add(Heartless.CLERIC_HEART.get(),
                        Heartless.BARBARIAN_HEART.get(),
                        Heartless.RANGER_HEART.get(),
                        Heartless.ROGUE_HEART.get());
    }
}
