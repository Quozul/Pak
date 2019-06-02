package me.quozul.fuald;

import me.quozul.fuald.enums.InventoryType;
import me.quozul.fuald.items.Inventory;

public class Player extends Entity {
    private Inventory COLLECTABLES;

    public Player(Inventory inventory) {
        super("Player", 20);

        this.COLLECTABLES = new Inventory();
        this.COLLECTABLES.setType(InventoryType.COLLECTABLES);

        this.setInventory(inventory);
        this.getInventory().setType(InventoryType.PLAYER_INVENTORY);
    }

    public Inventory getCollectables() {
        return this.COLLECTABLES;
    }

    public void setCollectables(Inventory inventory) {
        this.COLLECTABLES = inventory;
    }
}
