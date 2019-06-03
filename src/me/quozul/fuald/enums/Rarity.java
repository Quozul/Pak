package me.quozul.fuald.enums;

public enum Rarity {
    NEVER(0.000f),
    MYTHIC(0.125f),
    LEGENDARY(0.250f),
    EPIC(0.375f),
    ULTRARARE(0.500f),
    RARE(0.625f),
    UNCOMMON(0.750f),
    COMMON(0.875f),
    ALWAYS(1.000f);

    public final float label;

    Rarity(float label) {
        this.label = label;
    }
}
