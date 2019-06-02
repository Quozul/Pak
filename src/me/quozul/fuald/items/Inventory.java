package me.quozul.fuald.items;

import me.quozul.fuald.enums.InventoryType;
import me.quozul.fuald.events.InventoryChangedEvent;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    // listener
    public List<InventoryChangedEvent> listeners = new ArrayList<>();
    public void addInventoryChangedEventListener(InventoryChangedEvent listener) {
        listeners.add(listener);
    }

    // inventory logic
    private int SIZE;
    private int SELECTED;
    private InventoryType TYPE = InventoryType.INVENTORY;
    private List<ItemStack> ITEMS = new ArrayList<>();

    public static final int DEFAULT_SIZE = 10;

    /**
     * Creates a new inventory
     * @param size
     * @param items
     */
    public Inventory(Integer size, ItemStack... items) {
        this.setSize((size == 0) ? 2147483647 : size);
        for (ItemStack stack : items) {
            ITEMS.add(stack);
        }
    }

    /**
     * Creates a new empty inventory
     */
    public Inventory() {
        this.SIZE = DEFAULT_SIZE;
        this.ITEMS = new ArrayList<>();
    }

    public void empty() {
        this.ITEMS = new ArrayList<>();

        for (InventoryChangedEvent listener : listeners)
            listener.onInventoryChanged(this);
    }

    public void safeEmpty() {
        for (ItemStack itemStack : this.ITEMS)
            this.removeItemStack(itemStack);

        for (InventoryChangedEvent listener : listeners)
            listener.onInventoryChanged(this);
    }

    public void setSize(int size) {
        this.SIZE = (size == 0) ? 2147483647 : size;
    }

    public int getSize() {
        return this.SIZE;
    }

    /**
     * Adds ItemStacks to the inventory
     * @param itemStacks itemstacks to be added
     * @return amount of items added to the inventory
     */
    public int addItemStacks(ItemStack... itemStacks) {
        int added = 0;
        // for all itemstacks to be added in the inventory
        for (ItemStack itemStack : itemStacks)
            added += this.addItemStack(itemStack);

        return added;
    }

    /**
     * Adds one ItemStack to the inventory
     * @param itemStack ItemStack to add
     * @return amount of items added to the inventory
     */
    public int addItemStack(ItemStack itemStack) {
        Item item = itemStack.getItem();
        int amount = itemStack.getAmount();
        int added = 0;

        List<ItemStack> itemStacksToRemove = new ArrayList<>();
        for (ItemStack itemStack1 : this.ITEMS) {
            if (itemStack1.getItem() == item) {
                amount += itemStack1.getAmount();
                itemStacksToRemove.add(itemStack1);
            }
        }

        // clear inventory
        for (ItemStack itemStack1 : itemStacksToRemove)
            this.ITEMS.remove(itemStack1);

        System.out.println(String.format("Total amount of %s: %d", item.getName(), amount));

        int stack_size = item.getMaxStack();
        int stacks = amount / stack_size;
        int last_stack = amount % stack_size;

        System.out.println(String.format("Stack size: %d, full stacks: %s, last stack: %d, inv size: %d, inv full: %d", stack_size, stacks, last_stack, this.getSize(), this.ITEMS.size()));

        // add full stacks
        for (int i = 0; i < stacks; i++) {
            if (this.ITEMS.size() > this.getSize())
                break;

            System.out.println("Added full stack");
            this.ITEMS.add(new ItemStack(item, stack_size));
            added += stack_size;
        }

        // add last (not full) stack
        if (last_stack != 0 && this.ITEMS.size() < this.getSize()) {
            System.out.println("Added partial stack");
            this.ITEMS.add(new ItemStack(item, last_stack));
            added += last_stack;
        }

        for (InventoryChangedEvent listener : listeners)
            listener.onInventoryChanged(this);

        return added;
    }

    /**
     * Completely remove the specified ItemStack
     * @param itemStack ItemStack to remove
     * @return success
     */
    public boolean removeItemStack(ItemStack itemStack) {
        boolean success = false;

        if (this.ITEMS.contains(itemStack)) {
            this.ITEMS.remove(itemStack);
            success = true;
        } else {
            boolean containItem = false;
            int index = 0;

            for (ItemStack itemStack1 : this.ITEMS) {
                if (itemStack1.getItem() == itemStack.getItem()) {
                    index = this.ITEMS.lastIndexOf(itemStack1);
                    containItem = true;
                }
            }

            if (containItem) {
                this.ITEMS.get(index).addAmount(-itemStack.getAmount());
                success = true;
            }
        }

        for (InventoryChangedEvent listener : listeners)
            listener.onInventoryChanged(this);

        return success;
    }

    /**
     * Get ItemStack from index
     * @param index
     * @return
     */
    public ItemStack getItemStack(int index) {
        return this.ITEMS.get(index);
    }

    /**
     * Get index from ItemStack
     * @param itemStack
     * @return
     */
    public int getItemStack(ItemStack itemStack) {
        return this.ITEMS.lastIndexOf(itemStack);
    }

    public List<ItemStack> getItemStacks() {
        return this.ITEMS;
    }

    public ItemStack getSelectedItemStack() {
        return this.getItemStack(this.SELECTED);
    }

    public void setSelectedSlot(int slot) {
        this.SELECTED = slot;
    }

    public void setType(InventoryType type) {
        this.TYPE = type;
    }

    public InventoryType getType() {
        return this.TYPE;
    }

    /**
     * Move this inventory to the specified one
     * @param inventory Inventory to move ItemStacks to
     */
    public void moveTo(Inventory inventory) {
        for (ItemStack itemStack : this.getItemStacks()) {
            int added = inventory.addItemStack(itemStack);
            this.ITEMS.get(this.getItemStack(itemStack)).addAmount(-added);
        }
    }

    /**
     * Move the specified ItemStack from this inventory to the specified inventory
     * @param itemStack ItemStack to move
     * @param inventory Inventory to move ItemStack to
     */
    public void moveItemStackTo(ItemStack itemStack, Inventory inventory) {
        boolean itemRemoved = this.removeItemStack(itemStack);

        if (itemRemoved)
            inventory.addItemStack(itemStack);
    }
}
