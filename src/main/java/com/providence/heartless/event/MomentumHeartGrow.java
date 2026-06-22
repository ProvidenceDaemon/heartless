package com.providence.heartless.event;

import com.providence.heartless.Heartless;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = Heartless.MOD_ID)
public class MomentumHeartGrow {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        if(event.getEntity() instanceof Player && event.getSource().is(DamageTypes.FLY_INTO_WALL) && event.getAmount() >= 10){
            CuriosApi.getCuriosInventory(event.getEntity()).ifPresent(curiosInventory -> {
                if (curiosInventory.findCurio("heart", 0).isEmpty()) {
                    curiosInventory.setEquippedCurio("heart", 0, new ItemStack(Heartless.MOMENTUM_HEART.get()));
                }
            });
        }
    };
}