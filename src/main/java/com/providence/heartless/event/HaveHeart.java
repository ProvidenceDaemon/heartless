package com.providence.heartless.event;

import com.providence.heartless.Heartless;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.Random;
@Mod.EventBusSubscriber(modid = Heartless.MOD_ID)
public class HaveHeart {


    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event){
        if (event.getLevel().isClientSide()) return;
        Mob mob = event.getEntity();
        ArrayList<EntityType> validEntity = new ArrayList<>();
        validEntity.add(EntityType.ZOMBIE);
        validEntity.add(EntityType.HUSK);
        validEntity.add(EntityType.DROWNED);
        validEntity.add(EntityType.SKELETON);
        validEntity.add(EntityType.STRAY);
        validEntity.add(EntityType.PIGLIN);
        validEntity.add(EntityType.PILLAGER);
        validEntity.add(EntityType.WITCH);

        Random r = new Random();
        int rand = r.nextInt(0,75);
        if(validEntity.contains(mob.getType()))
            switch(rand){
                case 0:
                    CuriosApi.getCuriosInventory(mob).ifPresent(curiosInventory -> {
                        curiosInventory.setEquippedCurio("enemy_heart", 0, new ItemStack(Heartless.ROGUE_HEART.get()));
                    });
                    break;
                case 1:
                    CuriosApi.getCuriosInventory(mob).ifPresent(curiosInventory -> {
                        curiosInventory.setEquippedCurio("enemy_heart", 0, new ItemStack(Heartless.RANGER_HEART.get()));
                    });
                    break;
                case 2:
                    CuriosApi.getCuriosInventory(mob).ifPresent(curiosInventory -> {
                        curiosInventory.setEquippedCurio("enemy_heart", 0, new ItemStack(Heartless.BARBARIAN_HEART.get()));
                    });
                    break;
                case 3:
                    CuriosApi.getCuriosInventory(mob).ifPresent(curiosInventory -> {
                        curiosInventory.setEquippedCurio("enemy_heart", 0, new ItemStack(Heartless.CLERIC_HEART.get()));
                    });
                    break;
            }

        };
}

