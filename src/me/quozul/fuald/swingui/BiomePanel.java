package me.quozul.fuald.swingui;

import me.quozul.fuald.Game;
import me.quozul.fuald.Main;
import me.quozul.fuald.Player;
import me.quozul.fuald.enums.InventoryType;
import me.quozul.fuald.events.InventoryChangedEvent;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.ItemStack;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BiomePanel extends JPanel {
    public BiomePanel() {
        this.add(new LootInventory());
        this.add(new CollectButton());
        this.setBorder(BorderFactory.createTitledBorder("Biome"));
    }
}

class LootInventory extends JList implements MouseListener, InventoryChangedEvent {
    public LootInventory() {
        Main.game.getPlayer().getCollectables().addInventoryChangedEventListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void onInventoryChanged(Inventory inventory) {
        if (inventory.getType() != InventoryType.COLLECTABLES)
            return;

        System.out.println("Collectable inventory changed");

        DefaultListModel<String> dlm = new DefaultListModel<>();

        for (ItemStack itemStack : inventory.getItemStacks()) {
            String name = String.format("%d %s", itemStack.getAmount(), itemStack.getItem().getName());
            dlm.addElement(name);
        }

        this.setModel(dlm);
    }

    public void mouseClicked(MouseEvent event) {
        Game game = Main.game;

        game.getPlayer().getCollectables().setSelectedSlot(this.getSelectedIndex());
    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }
}

class CollectButton extends JButton implements MouseListener {
    public CollectButton() {
        super("Collect");
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Player player = Main.game.getPlayer();

        if (player.getCollectables().getItemStacks().size() <= 0)
            return;

        ItemStack selectedItemStack = player.getCollectables().getSelectedItemStack();

        player.getInventory().addItemStack(selectedItemStack);
        player.getCollectables().removeItemStack(selectedItemStack);
    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }
}