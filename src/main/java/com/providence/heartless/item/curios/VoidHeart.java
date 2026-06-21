package com.providence.heartless.item.curios;

import com.providence.heartless.Heartless;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import java.util.List;

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
    public static void onProjectileImpact(ProjectileImpactEvent event){
        event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);
        event.getEntity().playSound(SoundEvents.CHORUS_FRUIT_TELEPORT);
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        tooltips.add(Component.translatable("curios.modifiers.heart").withStyle(ChatFormatting.GOLD));
        tooltips.add(getDescription(stack));
        return super.getAttributesTooltip(tooltips, stack);
    }
}
