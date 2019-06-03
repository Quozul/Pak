package me.quozul.fuald.loots;

import me.quozul.fuald.enums.Rarity;
import me.quozul.fuald.items.Item;
import me.quozul.fuald.items.ItemStack;

public class LootStack extends ItemStack {
    private float CHANCE = 0;

    public LootStack(Item item, int amount, float chance) {
        super(item, amount);
        this.CHANCE = chance;
    }

    public float getChance() {
        return this.CHANCE;
    }

    public void setChance(int chance) {
        this.CHANCE = chance;
    }

    public void setChance(Rarity chance) {
        this.CHANCE = chance.label;
    }
}
