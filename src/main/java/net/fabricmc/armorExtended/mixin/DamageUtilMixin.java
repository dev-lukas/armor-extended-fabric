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
    float return_damage = damage * (1.0f - g / (r / dr));
    if(ArmorExtended.CONFIG.debugMode()) {
      ArmorExtended.LOGGER.info("Prevented Damage was: {} %", 100f - 100.0f * (1.0f - g / (r / dr)));
    }
		cir.setReturnValue(return_damage);
  }
}
