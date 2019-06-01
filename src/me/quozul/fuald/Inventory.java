package me.quozul.fuald;


import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int SIZE;
    private List<ItemStack> ITEMS = new ArrayList<>();

    public static final int DEFAULT_SIZE = 10;

    /**
     * Creates a new inventory
     * @param size
     * @param items
     */
    public Inventory(Integer size, ItemStack... items) {
        SIZE = size;
        for (ItemStack stack : items) {
            ITEMS.add(stack);
        }
    }

    /**
     * Creates a new empty inventory
     */
    public Inventory() {
        SIZE = this.DEFAULT_SIZE;
        ITEMS = new ArrayList<>();
    }

    /*
    Bug: items are duplicated in the entity's inventory
     */
    /**
     * Adds an item to the inventory
     * @param itemstacks itemstacks to be added
     */
    public void addItemStacks(ItemStack... itemstacks) {
        // for all itemstacks to be added in the inventory
        for (ItemStack item : itemstacks) {
            System.out.println(String.format("itemstack to add amound: %d", item.getAmount()));
            ItemStack bigStack = item;

            // for all itemstacks in the inventory
            List<ItemStack> itemStacksInInv = new ArrayList<>();
            for (ItemStack itemStack : ITEMS) {
                if (itemStack.getItem() == bigStack.getItem()) {
                    System.out.println(String.format("Item found, inventory amount: %d, already amount: %d", itemStack.getAmount(), bigStack.getAmount()));
                    bigStack.addAmount(itemStack.getAmount());
                    itemStacksInInv.add(itemStack);
                }
            }

            for (ItemStack itemstack : itemStacksInInv) {
                ITEMS.remove(itemstack);
            }

            int items = ITEMS.size();

            int stack_size = bigStack.getAmount();
            int max_stack_size = bigStack.getItem().getMaxStack();

            int stacks = stack_size / max_stack_size;
            int last_stack = stack_size % max_stack_size;

            System.out.println(String.format("Total %s items: %d, maximum stack size: %d, stacks: %d, last_stack: %d",
                    bigStack.getItem().getName(), stack_size, max_stack_size, stacks, last_stack));

            for (int i = 0; i < stacks; i++) {
                if (items > SIZE)
                    return;

                ITEMS.add(new ItemStack(bigStack.getItem(), max_stack_size));
            }

            if (items > SIZE)
                return;

            if (last_stack > 0) {
                ItemStack last_itemstack = new ItemStack(bigStack.getItem(), last_stack);
                ITEMS.add(last_itemstack);
            }
        }
    }

    public boolean removeItem(ItemStack item) {
        if (this.ITEMS.contains(item)) {
            this.ITEMS.remove(item);
            return true;
        } else {
            return false; // inventory don't containt this item
        }
    }

    public ItemStack getItemStack(int index) {
        return this.ITEMS.get(index);
    }

    public List<ItemStack> getItemStacks() {
        return this.ITEMS;
    }
}
