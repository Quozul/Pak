package me.quozul.fuald.swingui;

import me.quozul.fuald.Biome;
import me.quozul.fuald.Game;
import me.quozul.fuald.Main;
import me.quozul.fuald.Player;
import me.quozul.fuald.enums.InventoryType;
import me.quozul.fuald.events.BiomeChangedEvent;
import me.quozul.fuald.events.InventoryChangedEvent;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.ItemStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BiomePanel extends JPanel {
    public BiomePanel() {
        this.add(new LootInventory());
        this.add(new CollectButton());
        this.add(new BiomeName());
        this.add(new SearchItem());
        this.add(new OpenBenches());
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

        player.getCollectables().moveItemStackTo(selectedItemStack, player.getInventory());
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

class SearchItem extends JButton implements MouseListener {
    public SearchItem() {
        super("Search for item");
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Player player = Main.game.getPlayer();

        player.getCollectables().addItemStack(new ItemStack(Main.game.getBiome().getRandomCollectable(), 1));
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

class OpenBenches extends JButton implements MouseListener {
    public OpenBenches() {
        super("Workbenches");
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        new WorkbenchesFrame();
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

class BiomeName extends JLabel implements BiomeChangedEvent {
    public BiomeName() {
        this.setText("Not in a biome... yet");
        Main.game.addBiomeChangedEventListener(this);
    }

    @Override
    public void onBiomeChanged(Biome new_biome) {
        String biomeName = new_biome.getName();
        this.setText("Current biome: " + biomeName);
        new BiomeChangedAnnouncement(biomeName);
    }
}

class BiomeChangedAnnouncement extends JDialog {
    final BiomeChangedAnnouncement frame = this;

    public BiomeChangedAnnouncement(String biomeName) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(this.buildContentPane(biomeName));
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.setVisible(true);

        frame.addFocusListener(new FocusListener() {
            private boolean gained = false;

            @Override
            public void focusGained(FocusEvent e) {
                gained = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (gained) {
                    frame.dispose();
                }
            }
        });
    }

    public JPanel buildContentPane(String biomeName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 194, 106));

        Font f = new Font("serif", Font.PLAIN, 24);
        JLabel newBiome = new JLabel("New biome entered!");
        newBiome.setFont(f);

        //TODO center and vertical align text
        JLabel biomeNameLabel = new JLabel(biomeName);
        biomeNameLabel.setLayout(new GridBagLayout());

        panel.add(newBiome);
        panel.add(biomeNameLabel);

        return panel;
    }
}