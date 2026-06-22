package com.providence.heartless.item;

import com.providence.heartless.Heartless;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HeartlessCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Heartless.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HEARTLESS_TAB = CREATIVE_MODE_TABS.register("heartless_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Heartless.BARBARIAN_HEART.get()))
                    .title(Component.translatable("creativetab.heartless.heartless_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(Heartless.HEART_RIPPER.get());
                        output.accept(Heartless.CLERIC_HEART.get());
                        output.accept(Heartless.BARBARIAN_HEART.get());
                        output.accept(Heartless.ROGUE_HEART.get());
                        output.accept(Heartless.RANGER_HEART.get());
                        output.accept(Heartless.MOMENTUM_HEART.get());
                        output.accept(Heartless.BROKEN_MOMENTUM_HEART.get());
                        output.accept(Heartless.VOID_HEART.get());
                        output.accept(Heartless.QUEEN_HEART.get());
                    })
                    .build());

}
