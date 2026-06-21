package com.providence.heartless.event;

import com.providence.heartless.Heartless;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = Heartless.MOD_ID)
public class VoidHeartGrow {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        LivingEntity living = event.getEntity();
        if(living instanceof Player && event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)){
            if(living.getHealth() < 4){
                CuriosApi.getCuriosInventory(event.getEntity()).ifPresent(curiosInventory -> {
                    if (curiosInventory.findCurio("heart", 0).isEmpty()) {
                        curiosInventory.setEquippedCurio("heart", 0, new ItemStack(Heartless.VOID_HEART.get()));
                    }
                });
            }
        }
    };
}