package com.providence.heartless.item.curios;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class BrokenMomentumHeart extends HeartCurio{
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = new ImmutableMultimap.Builder<>();
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Speed Nerf", -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Damage Nerf", -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Attack Speed Nerf", -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return builder.build();
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        tooltips.add(getDescription(stack));
        return super.getAttributesTooltip(tooltips, stack);
    }
}
