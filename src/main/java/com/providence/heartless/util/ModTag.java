package com.providence.heartless.util;

import com.providence.heartless.Heartless;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTag {
    public static class Items{
        public static final TagKey<Item> ENEMY_HEART = curioTag("enemy_heart");
        public static final TagKey<Item> HEART = curioTag("heart");

        private static TagKey<Item> curioTag(String name){
            return ItemTags.create(new ResourceLocation("curios", name));
        }
    }
}
