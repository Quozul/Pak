package me.quozul.fuald;

public class Entity {
    private Inventory INVENTORY;
    private int HEALTH;
    private String NAME;

    private static final int DEFAULT_HEALTH = 10;

    /**
     * Creates a new entity
     * @param health health points of the entity
     * @param inventory inventory of the entity
     */
    public Entity(String name, int health, Inventory inventory) {
        this.NAME = name;
        this.HEALTH = health;
        this.INVENTORY = inventory;
    }

    /**
     * Creates a blank entity
     */
    public Entity() {
        this.NAME = "";
        this.HEALTH = DEFAULT_HEALTH;
        this.INVENTORY = new Inventory();
    }

    /**
     * Get the health of the entity
     * @return health
     */
    public int getHealth() {
        return this.HEALTH;
    }

    /**
     * Get the inventory of the entity
     * @return inventory of the entity
     */
    public Inventory getInventory() {
        return this.INVENTORY;
    }

    /**
     * Return name of the entity as string
     * @return name of the entity
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Sets the inventory of the entity
     * @param inventory inventory to set
     * @return
     */
    public void setInventory(Inventory inventory) {
        this.INVENTORY = inventory;
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
}
