package com.providence.heartless.item.curios;

import com.providence.heartless.Heartless;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.StatsCounter;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import java.util.List;
import static com.providence.heartless.Heartless.HEART_ATTACK;


public class MomentumHeart extends HeartCurio {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        final LivingEntity livingEntity = slotContext.entity();
        if (livingEntity.level().getGameTime() % 100L == 0 && !livingEntity.level().isClientSide()) {
            boolean increase = trackStatIncrease(slotContext, stack);
            //if the player has taken actions in the last second, increase level
            if(increase){
                int currentInertiaLevel = livingEntity.getEffect(Heartless.INERTIA.get()).getAmplifier();
                livingEntity.addEffect(new MobEffectInstance(Heartless.INERTIA.get(), 1000, currentInertiaLevel + 1));
            }
            //if the player has not taken actions in the last second, decrease level
            else{
                int currentInertiaLevel = livingEntity.getEffect(Heartless.INERTIA.get()).getAmplifier();
                if (currentInertiaLevel == 0){
                    CuriosApi.getCuriosInventory(livingEntity).ifPresent(curiosInventory -> {
                        curiosInventory.setEquippedCurio("heart", 0, new ItemStack(Heartless.BROKEN_MOMENTUM_HEART.get()));
                    });
                }
                else{
                    livingEntity.removeEffect(Heartless.INERTIA.get());
                    livingEntity.addEffect(new MobEffectInstance(Heartless.INERTIA.get(), 1000, currentInertiaLevel - 1));
                }
            }
        }
    }

    public boolean trackStatIncrease(SlotContext context, ItemStack stack){
        ServerPlayer player = ((ServerPlayer) context.entity());
        StatsCounter statsHandler = player.getStats();
        int blocksMoved = statsHandler.getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM)) + statsHandler.getValue(Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM)) +
                statsHandler.getValue(Stats.CUSTOM.get(Stats.WALK_ON_WATER_ONE_CM)) + statsHandler.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM)) +
                statsHandler.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM)) + statsHandler.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM));
        int damageDealt = statsHandler.getValue(Stats.CUSTOM.get(Stats.DAMAGE_DEALT));

        double movedDiff = (double) (blocksMoved - stack.getTag().getInt("BlocksMoved")) /200;
        double damageDiff = (double) (damageDealt - stack.getTag().getInt("DamageDealt")) /10;

        stack.getOrCreateTag().putInt("BlocksMoved", blocksMoved);
        stack.getOrCreateTag().putInt("DamageDealt", damageDealt);

        if((movedDiff + damageDiff * 2) >= 10){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!prevStack.is(Heartless.MOMENTUM_HEART.get())){
            slotContext.entity().addEffect(new MobEffectInstance(Heartless.INERTIA.get(), 1000, 24));
            ServerPlayer player = ((ServerPlayer) slotContext.entity());
            StatsCounter statsHandler = player.getStats();
            int blocksMoved = statsHandler.getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM)) + statsHandler.getValue(Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM)) +
                    statsHandler.getValue(Stats.CUSTOM.get(Stats.WALK_ON_WATER_ONE_CM)) + statsHandler.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM)) +
                    statsHandler.getValue(Stats.CUSTOM.get(Stats.SWIM_ONE_CM)) + statsHandler.getValue(Stats.CUSTOM.get(Stats.SPRINT_ONE_CM));
            int damageDealt = statsHandler.getValue(Stats.CUSTOM.get(Stats.DAMAGE_DEALT));

            stack.getOrCreateTag().putInt("BlocksMoved", blocksMoved);
            stack.getOrCreateTag().putInt("DamageDealt", damageDealt);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        final LivingEntity livingEntity = slotContext.entity();
        ServerPlayer player = ((ServerPlayer) slotContext.entity());
        assert stack.getTag() != null;
        if (!newStack.is(Heartless.MOMENTUM_HEART.get()) && !newStack.is(Heartless.BROKEN_MOMENTUM_HEART.get())) {
            stack.getOrCreateTag().putInt("BlocksMoved", 0);
            stack.getOrCreateTag().putInt("DamageDealt", 0);

            DamageSource damageSource = new DamageSource(livingEntity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HEART_ATTACK), livingEntity);
            if (!livingEntity.hasEffect(MobEffects.REGENERATION)) {
                livingEntity.hurt(damageSource, livingEntity.getHealth() + livingEntity.getAbsorptionAmount());
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
