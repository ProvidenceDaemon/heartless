package com.providence.heartless.item.weapons;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import static com.providence.heartless.util.ModTag.Items.HEART;


public class HeartRipper extends SwordItem {
    public HeartRipper(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    //Credit to lodestar, method from lodestone library
    public static void spawnItemOnEntity(LivingEntity entity, ItemStack stack) {
        Level level = entity.level();
        ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY() + 0.5, entity.getZ(), stack);
        itemEntity.setPickUpDelay(40);
        itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(0, 1, 0));
        level.addFreshEntity(itemEntity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target.getHealth() < target.getMaxHealth()/10){
            CuriosApi.getCuriosInventory(target).ifPresent(curiosInventory -> {
                if(curiosInventory.findCurio("enemy_heart", 0).get().stack().is(HEART)){
                    spawnItemOnEntity(attacker, curiosInventory.findCurio("enemy_heart", 0).get().stack());
                    curiosInventory.setEquippedCurio("enemy_heart", 0, ItemStack.EMPTY);
                }
                else if (curiosInventory.findCurio("heart", 0).get().stack().is(HEART)) {

                    spawnItemOnEntity(attacker, curiosInventory.findCurio("heart", 0).get().stack());
                    curiosInventory.setEquippedCurio("heart", 0, ItemStack.EMPTY);
                }
            });
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}

