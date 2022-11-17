package net.fabricmc.armorExtended.mixin;

import net.fabricmc.armorExtended.ArmorExtended;
import net.minecraft.entity.DamageUtil;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DamageUtil.class)
public class DamageUtilMixin {
	@Inject(method = "getDamageLeft(FFF)F", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
	private static void getDamageLeft(float damage, float armor, float armorToughness, CallbackInfoReturnable<Float> cir) {    
    float r = ArmorExtended.CONFIG.maxUsableArmor();
    float dr = ArmorExtended.CONFIG.maxDamageReduction();
    float f = 2.0f + armorToughness / 4.0f;
    float g = MathHelper.clamp(armor - damage / f, armor * 0.2f, r);
    float p;
    if(ArmorExtended.CONFIG.useLogCurve()) {
        p = (float) Math.log10(1 + 9 * (g / r)) * dr;
    } else {
        p = dr * (g / r);
    }

    float return_damage = damage * (1.0f - p);
    if(ArmorExtended.CONFIG.debugMode()) {
      ArmorExtended.LOGGER.info("Damage before reduction: {}", damage);
      ArmorExtended.LOGGER.info("Reduction percentage: {} %", 100.0f * p);
      ArmorExtended.LOGGER.info("Damage after reduction: {}", return_damage);
    }
		cir.setReturnValue(return_damage);
  }
}
