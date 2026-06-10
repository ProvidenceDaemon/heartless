package com.providence.heartless.event;

import com.providence.heartless.Heartless;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.Collection;



@Mod.EventBusSubscriber(modid = Heartless.MOD_ID)
public class QueenHeartGrow {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event){
        Entity killer = event.getSource().getEntity();
        LivingEntity victim = event.getEntity();
        assert killer != null;
        if(killer instanceof Player && victim instanceof WitherBoss){
            Collection<MobEffectInstance> killerEffects = ((Player) killer).getActiveEffects();
            Collection<MobEffectInstance> killerPosEffects = new ArrayList<>();
            Collection<MobEffectInstance> killerNegEffects = new ArrayList<>();

            for (MobEffectInstance effect : killerEffects){
                if(effect.getEffect().isBeneficial()){
                    killerPosEffects.add(effect);
                }
                else{
                    killerNegEffects.add(effect);
                }
            }
            if(killerPosEffects.size() >= 3 && killerNegEffects.size() >=3 ){
                CuriosApi.getCuriosInventory((Player) killer).ifPresent(curiosInventory -> {
                    if (curiosInventory.findCurio("heart", 0).isEmpty()) {
                        curiosInventory.setEquippedCurio("heart", 0, new ItemStack(Heartless.QUEEN_HEART.get()));
                    }
                });
            }
        }
    };
}

