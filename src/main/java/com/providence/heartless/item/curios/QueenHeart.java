package com.providence.heartless.item.curios;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenHeart extends HeartCurio {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        final LivingEntity livingEntity = slotContext.entity();
        if (livingEntity.level().getGameTime() % 20L == 0) {
            Collection<MobEffectInstance> effects = livingEntity.getActiveEffects();
            List<MobEffectInstance> activeEffects = new ArrayList<MobEffectInstance>(effects);
            for(MobEffectInstance effect : activeEffects){
                MobEffectInstance newEffect = new MobEffectInstance(effect.getEffect(), effect.getDuration()+10, effect.getAmplifier(), effect.isAmbient(), effect.isVisible());
                livingEntity.addEffect(newEffect);
            }
        }
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        tooltips.add(Component.translatable("curios.modifiers.heart").withStyle(ChatFormatting.GOLD));
        tooltips.add(getDescription(stack));
        return super.getAttributesTooltip(tooltips, stack);
    }
}
