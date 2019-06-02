package me.quozul.fuald;

import me.quozul.fuald.enums.EntityType;
import me.quozul.fuald.enums.ItemType;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.Item;
import me.quozul.fuald.items.ItemStack;
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
        porkshop.setType(ItemType.EATABLE);
        Item steak = new Item("Steak");
        steak.setMaxStack(4);
        steak.setType(ItemType.EATABLE);
        Item dragon_tooth = new Item("Dragon tooth");
        steak.setMaxStack(1);
        steak.setType(ItemType.USELESS);

        LootInventory coige_loots = new LootInventory();

        coige_loots.addLootStacks(
                new LootStack(porkshop, 2, 0.5f),
                new LootStack(steak, 1, 1f)
        );

        Item stick = new Item("Stick");
        stick.setType(ItemType.WEAPON);
        stick.setAttackDamage(2);
        Inventory human_inventory = new Inventory(2, new ItemStack(stick, 1));

        Entity coige = new Entity("Coige", 24, coige_loots);
        coige.setType(EntityType.NEUTRAL);
        Entity pig = new Entity("Pig", 10, new LootInventory(new LootStack(porkshop, 1, 1f)));
        pig.setType(EntityType.PASSIVE);
        Entity cow = new Entity("Cow", 12, new LootInventory(new LootStack(steak, 2, 0.75f)));
        cow.setType(EntityType.PASSIVE);
        Entity dragon = new Entity("Dragon", 50, new LootInventory(
                new LootStack(dragon_tooth, 1, 0.25f), new LootStack(steak, 4, 0.5f)
        ));
        coige.setType(EntityType.AGGRESSIVE);
        Entity human = new Entity("Human", 4, new LootInventory());
        cow.setType(EntityType.AGGRESSIVE);

        biome.registerEntity(
                pig,
                cow,
                coige,
                dragon,
                human
        );

        Main.game = new Game(biome);
        game.setBiome(biome);

        UI = new JavaSwing();
    }
}
