package com.providence.heartless.item.curios;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.providence.heartless.util.AttributeContainer;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

import static com.providence.heartless.Heartless.HEART_ATTACK;

public class HeartCurio extends Item implements ICurioItem {
    public HeartCurio() {
        super(new Item.Properties().stacksTo(1).defaultDurability(0));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        final LivingEntity livingEntity = slotContext.entity();
        DamageSource damageSource = DamageTypeHelper.create(HEART_ATTACK, livingEntity);
        if(!livingEntity.hasEffect(MobEffects.REGENERATION)){
            livingEntity.hurt(damageSource,livingEntity.getHealth()+livingEntity.getAbsorptionAmount());
        }
    }


    private List<AttributeContainer> attributeTemplates = List.of();
    /**
     * Generates the attribute modifiers for this Curio when equipped.
     * <p>
     * To prevent UUID collisions between different attributes on the same item, this method
     * generates unique UUIDs by XORing the slot-provided UUID with the hash of the attribute's
     * description ID and the index of the template.
     * </p>
     *
     * @param slotContext Context regarding the Curio slot being queried.
     * @param uuid        The base UUID provided by the Curios API for this slot.
     * @param stack       The specific ItemStack being equipped.
     * @return A multimap of attributes and their respective modifiers.
     */
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        if (this.attributeTemplates.isEmpty()) {
            return ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        }

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        for (int i = 0; i < this.attributeTemplates.size(); i++) {
            AttributeContainer holder = this.attributeTemplates.get(i);

            // Deterministic unique UUID generation per attribute
            UUID attributeUUID = new UUID(
                    uuid.getMostSignificantBits() ^ holder.attribute().getDescriptionId().hashCode(),
                    uuid.getLeastSignificantBits() ^ i
            );
            AttributeModifier modifier = holder.createModifier(attributeUUID);

            builder.put(holder.attribute(), modifier);
        }

        return builder.build();
    }
}
