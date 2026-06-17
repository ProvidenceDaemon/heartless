package com.providence.heartless.datagen;

import com.providence.heartless.Heartless;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static com.providence.heartless.Heartless.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Heartless.MOD_ID, existingFileHelper);
    }

    @Override
    public void registerModels() {
        simpleItem(BARBARIAN_HEART);
        simpleItem(ROGUE_HEART);
        simpleItem(QUEEN_HEART);
        simpleItem(RANGER_HEART);
        simpleItem(CLERIC_HEART);
        simpleItem(BROKEN_MOMENTUM_HEART);
        simpleItem(MOMENTUM_HEART);
        handheldItem(HEART_RIPPER);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Heartless.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<SwordItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("minecraft:item/handheld")).texture("layer0",
                new ResourceLocation(Heartless.MOD_ID,"item/" + item.getId().getPath()));
    }
}