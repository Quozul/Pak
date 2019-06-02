package me.quozul.fuald.loots;

import me.quozul.fuald.enums.InventoryType;
import me.quozul.fuald.items.Inventory;

import java.util.ArrayList;
import java.util.List;

public class LootInventory extends Inventory {
    private List<LootStack> ITEMS = new ArrayList<>();
    private InventoryType TYPE = InventoryType.LOOT_INVENTORY;

    /**
     * Creates a LootInventory, used to set the loots of an entity
     * LootInventory don't have size limit
     * @param lootStacks LootStacks that the entity will drop
     */
    public LootInventory(LootStack... lootStacks) {
        for (LootStack stack : lootStacks) {
            this.ITEMS.add(stack);
        }
    }

    /**
     * Adds LootStacks to the LootInventory
     * @param lootStacks
     */
    public void addLootStacks(LootStack... lootStacks) {
        for (LootStack stack : lootStacks) {
            addLootStack(stack);
        }
    }

    /**
     * Adds a LootStack to the LootInventory
     * @param lootStack
     */
    public void addLootStack(LootStack lootStack) {
        this.ITEMS.add(lootStack);
    }

    public List<LootStack> getLootStacks() {
        return this.ITEMS;
    }

    /**
     * Gets a inventory than from the LootInventory using the chance of LootStacks
     * @return
     */
    public Inventory getLootedInventory() {
        Inventory inv = new Inventory();

        for (LootStack lootStack : this.getLootStacks()) {
            if (lootStack.getChance() > Math.random()) {
                inv.addItemStack(lootStack);
                System.out.println(lootStack.getItem().getName() + " looted");
            }
        }

        return inv;
    }
}
