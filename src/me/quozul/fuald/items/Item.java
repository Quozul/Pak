package me.quozul.fuald.items;

import me.quozul.fuald.enums.ItemType;

public class Item {
    private String NAME;
    private int MAX_STACK = 32767;
    private ItemType ITEM_TYPE = ItemType.USELESS;
    private int ATTACK_DAMAGE = 0;

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

    public void setType(ItemType itemType) {
        this.ITEM_TYPE = itemType;
    }

    public ItemType getItemType() {
        return this.ITEM_TYPE;
    }

    public int getAttackDamage() {
        return this.ATTACK_DAMAGE;
    }

    public void setAttackDamage(int damage) {
        this.ATTACK_DAMAGE = damage;
    }
}
