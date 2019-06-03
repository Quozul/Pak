package me.quozul.fuald.swingui;

import me.quozul.fuald.Entity;
import me.quozul.fuald.events.DeathEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JavaSwing extends JFrame implements DeathEvent {
    public JavaSwing() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle("Pak Java edition");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.buildContentPane());

        // menu bar
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Plugins");
        JMenuItem size = new JMenuItem("Load plugins");
        menu.add(size);
        menubar.add(menu);
        this.setJMenuBar(menubar);
        size.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new PluginFrame();
                } catch (ClassNotFoundException | IOException | InstantiationException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.setVisible(true);
    }

    private JPanel buildContentPane() {
        // inventory panel
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.setBackground(new Color(224, 235, 255));

        inventoryPanel.add(new InventoryPanel());
        inventoryPanel.add(new BiomePanel());

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBackground(new Color(248, 255, 232));

        JPanel controlPanel = new JPanel();
        controlPanel.add(new NewTurnButton());
        mainPanel.add(controlPanel);

        mainPanel.add(new EntityPanel());
        mainPanel.add(inventoryPanel);

        System.out.println("Panels built");

        return mainPanel;
    }

    // events
    @Override
    public void onEntityDie(Entity killer, Entity victim) {
        System.out.println("Entity killed");
    }
}
