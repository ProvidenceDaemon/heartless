package com.providence.heartless.effect;

import com.providence.heartless.Heartless;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import team.lodestar.lodestone.helpers.ColorHelper;

import java.util.ArrayList;
import java.util.List;

public class InertiaEffect extends MobEffect {
    public InertiaEffect() {
        super(MobEffectCategory.NEUTRAL, ColorHelper.getColor(191, 55, 212));
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "dd68fe02-68d5-11f1-9e54-325096b39f47", +0.01, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "02807bd4-68d6-11f1-8cd6-325096b39f47", +0.01, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        return ret;
    }
}
