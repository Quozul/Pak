package me.quozul.fuald.swingui;

import me.quozul.fuald.Game;
import me.quozul.fuald.Main;
import me.quozul.fuald.Player;
import me.quozul.fuald.enums.InventoryType;
import me.quozul.fuald.events.InventoryChangedEvent;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.Item;
import me.quozul.fuald.items.ItemStack;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InventoryPanel extends JPanel {
    public InventoryPanel() {
        this.add(new InventoryList());
        this.add(new DropButton());
        this.add(new AttackButton());
        this.add(new InformationsButton());
        this.setBorder(BorderFactory.createTitledBorder("Inventory"));
    }
}

class InventoryList extends JList implements MouseListener, InventoryChangedEvent {
    public InventoryList() {
        Main.game.getPlayer().getInventory().addInventoryChangedEventListener(this);
        onInventoryChanged(Main.game.getPlayer().getInventory());
        this.setSelectedIndex(0);
        this.addMouseListener(this);
    }

    @Override
    public void onInventoryChanged(Inventory inventory) {
        if (inventory.getType() != InventoryType.PLAYER_INVENTORY)
            return;

        System.out.println("Player inventory changed");

        DefaultListModel<String> dlm = new DefaultListModel<>();

        for (ItemStack itemStack : inventory.getItemStacks()) {
            String name = String.format("%d %s", itemStack.getAmount(), itemStack.getItem().getName());
            dlm.addElement(name);
        }

        this.setModel(dlm);
}

    public void mouseClicked(MouseEvent event) {
        Game game = Main.game;

        System.out.println("Slot changed");
        game.getPlayer().getInventory().setSelectedSlot(this.getSelectedIndex());
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

class DropButton extends JButton implements MouseListener {
    public DropButton() {
        super("Drop");
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Player player = Main.game.getPlayer();

        if (player.getInventory().getItemStacks().size() <= 0)
            return;

        ItemStack selectedItemStack = player.getInventory().getSelectedItemStack();
        player.getInventory().moveItemStackTo(selectedItemStack, player.getCollectables());
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

class AttackButton extends JButton implements MouseListener {
    public AttackButton() {
        super("Attack");
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Game game = Main.game;

        Item attackItem = (game.getPlayer().getInventory().getItemStacks().size() > 0) ?
                game.getPlayer().getInventory().getSelectedItemStack().getItem() : new Item("Fists");
        game.getTurn().attack(game.getPlayer(), game.getTurn().getEntity(), attackItem);
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

class InformationsButton extends JButton implements MouseListener {
    public InformationsButton() {
        super("Informations");
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Game game = Main.game;

        ItemStack selectedItem = (game.getPlayer().getInventory().getItemStacks().size() > 0) ?
                game.getPlayer().getInventory().getSelectedItemStack() : new ItemStack(new Item("Fists"), 1);
        new ItemFrame(selectedItem);
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