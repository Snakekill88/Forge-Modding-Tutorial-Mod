package net.palermod.tutorialMod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties GingerBrave = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(4)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,800,2), 0.2f)
            .alwaysEdible()
            .build();
}
