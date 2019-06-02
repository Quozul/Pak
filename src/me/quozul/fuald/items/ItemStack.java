package me.quozul.fuald.items;

public class ItemStack {
    private Item ITEM;
    private int AMOUNT;

    /**
     * Creates a new ItemStack with specified item and amount
     * @param item the item stored in
     * @param amount the amount of items
     */
    public ItemStack(Item item, int amount) {
        this.ITEM = item;
        this.AMOUNT = amount;
    }

    /**
     * Creates a blank ItemStack
     */
    public ItemStack() { }

    /**
     * Merge 2 item stacks
     * @param itemStack1 1st stack
     * @param itemStack2 2nd stack
     */
    public ItemStack(ItemStack itemStack1, ItemStack itemStack2) {
        if (itemStack1.getItem() == itemStack2.getItem()) {
            this.AMOUNT = itemStack1.getAmount() + itemStack2.getAmount();
            this.ITEM = itemStack1.getItem();
        }
    }

    /**
     * Get the item of the stack
     * @return
     */
    public Item getItem() {
        return this.ITEM;
    }

    /**
     * Get the amount of items in the stack
     * @return
     */
    public int getAmount() {
        return this.AMOUNT;
    }

    /**
     * Sets the amount of item in the stack
     * @param amount
     */
    public void setAmount(int amount) {
        this.AMOUNT = amount;
    }

    /**
     * Add the specified amount
     * @param amount
     */
    public void addAmount(int amount) {
        this.AMOUNT += amount;
    }

    /**
     * Sets the item stored in the stack
     * @param item
     */
    public void setItem(Item item) {
        this.ITEM = item;
    }
}
