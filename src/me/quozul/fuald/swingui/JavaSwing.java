package me.quozul.fuald.swingui;

import me.quozul.fuald.ItemStack;
import me.quozul.fuald.Main;
import me.quozul.fuald.Turn;
import me.quozul.fuald.events.NewTurnEvent;

import javax.swing.*;
import java.awt.*;

public class JavaSwing extends JFrame implements NewTurnEvent {
    private JLabel mob_field = new JLabel();
    private JList inventory_items = new JList();


    public JavaSwing() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle("Fuald Java edition");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.buildContentPane());
        this.setVisible(true);
    }

    private JPanel buildContentPane() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBackground(new Color(255, 255, 255));

        JPanel gamePanel = new JPanel();
        JPanel controlPanel = new JPanel();

        gamePanel.add(mob_field);
        updateInventory();
        gamePanel.add(inventory_items);

        controlPanel.add(new AttackButton());
        controlPanel.add(new NewTurnButton());

        mainPanel.add(gamePanel);
        mainPanel.add(controlPanel);
        System.out.println("Panel built");

        return mainPanel;
    }

    @Override
    public void onNewTurnStarted(Turn turn) {
        mob_field.setText(turn.getEntity().getName());
        updateInventory();
    }

    public void updateInventory() {
        DefaultListModel dlm = new DefaultListModel();

        for (ItemStack itemStack : Main.game.getPlayer().getInventory().getItemStacks()) {
            String name = String.format("%d %s", itemStack.getAmount(), itemStack.getItem().getName());
            dlm.addElement(name);
        }

        inventory_items.setModel(dlm);
    }
}
