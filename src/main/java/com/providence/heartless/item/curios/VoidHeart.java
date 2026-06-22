package com.providence.heartless.item.curios;

import com.providence.heartless.Heartless;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import java.util.List;

import static net.minecraft.world.entity.Entity.RemovalReason.DISCARDED;

@Mod.EventBusSubscriber(modid = Heartless.MOD_ID)
public class VoidHeart extends HeartCurio {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        final Player player = (Player) slotContext.entity();
        if (player.level().getGameTime() % 150L == 0) {
            player.causeFoodExhaustion(1.0F);
        }
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event){
        if(event.getEntity() instanceof Player){
            if(event.getSource().is(DamageTypeTags.IS_PROJECTILE)){
                CuriosApi.getCuriosInventory(event.getEntity()).ifPresent(curiosInventory -> {
                    if (curiosInventory.isEquipped(Heartless.VOID_HEART.get())) {
                        event.getEntity().playSound(SoundEvents.CHORUS_FRUIT_TELEPORT);
                        event.getSource().getDirectEntity().setRemoved(DISCARDED);
                        event.setCanceled(true);
                    }
                });
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
