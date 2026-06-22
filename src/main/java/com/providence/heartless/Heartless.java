package com.providence.heartless;

import com.providence.heartless.effect.InertiaEffect;
import com.providence.heartless.item.curios.*;
import com.providence.heartless.item.weapons.HeartRipper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.providence.heartless.item.HeartlessCreativeTab.CREATIVE_MODE_TABS;

@Mod(Heartless.MOD_ID)

public class Heartless {

    public static final String MOD_ID = "heartless";
    public static final ResourceKey<DamageType> HEART_ATTACK = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Heartless.MOD_ID, "heart_attack"));
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Heartless.MOD_ID);

    public static final RegistryObject<Item> ROGUE_HEART = ITEMS.register("rogue_heart", RogueHeart::new);
    public static final RegistryObject<Item> RANGER_HEART = ITEMS.register("ranger_heart", RangerHeart::new);
    public static final RegistryObject<Item> BARBARIAN_HEART = ITEMS.register("barbarian_heart", BarbarianHeart::new);
    public static final RegistryObject<Item> CLERIC_HEART = ITEMS.register("cleric_heart", ClericHeart::new);
    public static final RegistryObject<Item> QUEEN_HEART = ITEMS.register("queen_heart", QueenHeart::new);
    public static final RegistryObject<Item> VOID_HEART = ITEMS.register("void_heart", VoidHeart::new);
    public static final RegistryObject<Item> MOMENTUM_HEART = ITEMS.register("momentum_heart", MomentumHeart::new);
    public static final RegistryObject<Item> BROKEN_MOMENTUM_HEART = ITEMS.register("broken_momentum_heart", BrokenMomentumHeart::new);

    public static final RegistryObject<SwordItem> HEART_RIPPER = ITEMS.register("heart_ripper", () -> new HeartRipper(HeartlessTiers.RIPPER, 1, -2.4F, new Item.Properties()));

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Heartless.MOD_ID);
    public static final RegistryObject<MobEffect> INERTIA = EFFECTS.register("inertia", InertiaEffect::new);

    public enum HeartlessTiers implements Tier {
        RIPPER(3, 121, 8.0F, 0.0F, 15, () -> {
            return Ingredient.of(Items.BONE);
        });

        private final int level;
        private final int uses;
        private final float speed;
        private final float damage;
        private final int enchantmentValue;
        private final LazyLoadedValue<Ingredient> repairIngredient;

        private HeartlessTiers(int level, int uses, float speed, float damage, int enchValue, Supplier<Ingredient> repairIngredient) {
            this.level = level;
            this.uses = uses;
            this.speed = speed;
            this.damage = damage;
            this.enchantmentValue = enchValue;
            this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        }

        public int getUses() {
            return this.uses;
        }

        public float getSpeed() {
            return this.speed;
        }

        public float getAttackDamageBonus() {
            return this.damage;
        }

        public int getLevel() {
            return this.level;
        }

        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
    }

    public Heartless() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}