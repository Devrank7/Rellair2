package org.rellair2.com.mixin;

import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwordItem.class)
public class MixSword {
    @Inject(method = "hurtEnemy", at = @At("RETURN"))
    public void hurtEnemyOn(CallbackInfoReturnable<Boolean> cir) {
        System.err.println("HURT");
    }

}
