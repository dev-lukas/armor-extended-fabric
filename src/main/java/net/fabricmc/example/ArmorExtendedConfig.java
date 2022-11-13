package net.fabricmc.example;

import io.wispforest.owo.config.annotation.Config;
@Config(name = "armor-extended", wrapperName = "ArmorExtendedConfig")
public class ArmorExtendedConfig {
    public int maxUsableArmor = 40;
    public int maxDamageReduction = 90;
}
