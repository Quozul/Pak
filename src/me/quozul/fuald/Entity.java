package me.quozul.fuald;

import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.loots.LootInventory;

public class Entity implements Cloneable {
    private Inventory INVENTORY;
    private LootInventory LOOT_INVENTORY;
    private int HEALTH;
    private String NAME;

    private static final int DEFAULT_HEALTH = 10;

    /**
     * Creates a new entity
     * @param name name of the entity
     * @param health health points of the entity
     * @param loot_inventory inventory that the entity drops
     * @param inventory inventory that the entity have
     */
    public Entity(String name, int health, LootInventory loot_inventory, Inventory inventory) {
        this.NAME = name;
        this.HEALTH = health;
        this.INVENTORY = inventory;
        this.LOOT_INVENTORY = loot_inventory;
    }

    public Entity(String name, int health, LootInventory inventory) {
        this.NAME = name;
        this.HEALTH = health;
        this.INVENTORY = new Inventory();
        this.LOOT_INVENTORY = inventory;
    }

    public Entity(String name, int health) {
        this.NAME = name;
        this.HEALTH = health;
        this.INVENTORY = new Inventory();
        this.LOOT_INVENTORY = new LootInventory();
    }

    /**
     * Creates a blank entity
     */
    public Entity() {
        this.NAME = "";
        this.HEALTH = DEFAULT_HEALTH;
        this.INVENTORY = new Inventory();
        this.LOOT_INVENTORY = new LootInventory();
    }

    /**
     * Get the health of the entity
     * @return health
     */
    public int getHealth() {
        return this.HEALTH;
    }

    /**
     * Sets the health of the entity to the amount given
     * @param new_health
     */
    public void setHealth(int new_health) {
        this.HEALTH = new_health;
    }

    /**
     * Add specified amount of health to the entity
     * Can remove health if negative number
     * @param health
     */
    public void addHealth(int health) {
        this.HEALTH += health;
    }

    /**
     * Get the inventory of the entity
     * @return inventory of the entity
     */
    public Inventory getInventory() {
        return this.INVENTORY;
    }

    /**
     * Sets the inventory of the entity
     * @param inventory inventory to set
     * @return
     */
    public void setInventory(Inventory inventory) {
        this.INVENTORY = inventory;
    }

    public LootInventory getLootInventory() {
        return this.LOOT_INVENTORY;
    }

    public void setLootInventory(LootInventory inv) {
        this.LOOT_INVENTORY = inv;
    }

    /**
     * Return name of the entity as string
     * @return name of the entity
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Return if the entity can be whether tamed or not
     * @return boolean
     */
    public boolean isTameable() {
        return false;
    }

    /**
     * Interract with the entity, base entity can't be interacted
     */
    public void interract() {
        return;
    }

    @Override
    public Entity clone() {
        return new Entity(this.NAME, this.HEALTH, this.LOOT_INVENTORY, this.INVENTORY);
    }
}
