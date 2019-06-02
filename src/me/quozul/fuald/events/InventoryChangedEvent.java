package me.quozul.fuald.events;

import me.quozul.fuald.items.Inventory;

public interface InventoryChangedEvent {
    void onInventoryChanged(Inventory inventory);
}
