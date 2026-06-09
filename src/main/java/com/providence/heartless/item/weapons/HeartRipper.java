package com.providence.heartless.item.weapons;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import top.theillusivec4.curios.api.CuriosApi;

import static com.providence.heartless.util.ModTag.Items.HEART;
import static team.lodestar.lodestone.helpers.ItemHelper.spawnItemOnEntity;

public class HeartRipper extends SwordItem {
    public HeartRipper(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
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

