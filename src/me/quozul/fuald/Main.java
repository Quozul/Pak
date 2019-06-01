package me.quozul.fuald;

import me.quozul.fuald.swingui.JavaSwing;

public class Main {
    public static Game game;

    public static void main(String[] args) {
        Biome biome = new Biome();

        Item porkshop = new Item("Porkshop");
        porkshop.setMaxStack(5);
        Item steak = new Item("Steak");
        steak.setMaxStack(4);

        Inventory coige_inv = new Inventory();

        coige_inv.addItemStacks(
                new ItemStack(porkshop, 2),
                new ItemStack(steak, 1)
        );

        biome.registerEntity(
                new Entity("Pig", 10, new Inventory(1, new ItemStack(porkshop, 1))),
                new Entity("Cow", 12, new Inventory(1, new ItemStack(steak, 2))),
                new Entity("Coige", 24, coige_inv)
        );

        Main.game = new Game(biome);
        game.setBiome(biome);

        JavaSwing UI = new JavaSwing();
        Main.game.addNewTurnStartedListener(UI);
    }
}
