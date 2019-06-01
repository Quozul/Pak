package me.quozul.fuald;

public class Item {
    private String NAME;
    private int MAX_STACK;

    /**
     * Create a new Item object for the game
     * @param name Name of the item.
     * @return
     */
    public Item(String name) {
        this.NAME = name;
    }

    /**
     * Sets the maximum of items that can be in one ItemStack
     * @param stack
     */
    public void setMaxStack(int stack) {
        MAX_STACK = stack;
    }

    /**
     * Return maximum stack
     * @return
     */
    public int getMaxStack() {
        return this.MAX_STACK;
    }

    /**
     * Return name of Item
     * @return
     */
    public String getName() {
        return NAME;
    }
}
