package me.quozul.fuald.craft;

import me.quozul.fuald.enums.CraftSuccess;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
    ItemStack OUTPUT;
    List<ItemStack> INPUT = new ArrayList<>();

    public Recipe(ItemStack output, ItemStack... inputs) {
        this.OUTPUT = output;
        this.INPUT.addAll(Arrays.asList(inputs));
    }

    public CraftSuccess craft(Inventory inventory) {
        // if inventory is full
        if (inventory.getItemStacks().size() >= inventory.getSize())
            return CraftSuccess.INVENRY_FULL;

        // this loop verify if the inventory contains enough of all items to craft the element
        for (ItemStack ingredient : this.INPUT) {
            ItemStack in_inventory = inventory.containsItem(ingredient.getItem());
            // don't contains enough of the item
            if (in_inventory.getAmount() < ingredient.getAmount())
                return CraftSuccess.NOT_ENOUGH_ITEMS;
        }

        // contains enough of every items
        for (ItemStack ingredient : this.INPUT)
            inventory.removeItemStack(ingredient);

        inventory.addItemStack(OUTPUT);

        return CraftSuccess.SUCCESS;
    }

    public ItemStack getOutputItemStack() {
        return this.OUTPUT;
    }

    public List<ItemStack> getInputItemStack() {
        return this.INPUT;
    }
}
