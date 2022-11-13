package net.fabricmc.armorExtended;

import io.wispforest.owo.config.Option.SyncMode;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.Sync;
@Config(name = "armor-extended", wrapperName = "ArmorExtendedConfig")
public class ArmorExtendedConfigModel {
    @Sync(SyncMode.OVERRIDE_CLIENT)
    @RangeConstraint(min = 1, max = 240)
    public float maxUsableArmor = 40f;

    @Sync(SyncMode.OVERRIDE_CLIENT)
    @RangeConstraint(min = 0.01f, max = 1f)
    public float maxDamageReduction = 0.9f;

    public boolean debugMode = false;
}
