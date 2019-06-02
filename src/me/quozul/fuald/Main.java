package me.quozul.fuald;

import me.quozul.fuald.items.Item;
import me.quozul.fuald.loots.LootInventory;
import me.quozul.fuald.loots.LootStack;
import me.quozul.fuald.swingui.JavaSwing;

public class Main {
    public static Game game;
    public static JavaSwing UI;

    public static void main(String[] args) {
        Biome biome = new Biome();

        Item porkshop = new Item("Porkshop");
        porkshop.setMaxStack(5);
        Item steak = new Item("Steak");
        steak.setMaxStack(4);

        LootInventory coige_inv = new LootInventory();

        coige_inv.addLootStacks(
                new LootStack(porkshop, 2, 0.5f),
                new LootStack(steak, 1, 1f)
        );

        biome.registerEntity(
                new Entity("Pig", 10, new LootInventory(new LootStack(porkshop, 1, 1f))),
                new Entity("Cow", 12, new LootInventory(new LootStack(steak, 2, 0.75f))),
                new Entity("Coige", 24, coige_inv)
        );

        Main.game = new Game(biome);
        game.setBiome(biome);

        UI = new JavaSwing();
    }
}
