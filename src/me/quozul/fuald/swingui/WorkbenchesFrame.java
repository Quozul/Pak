package me.quozul.fuald.swingui;

import me.quozul.fuald.Main;
import me.quozul.fuald.craft.Bench;
import me.quozul.fuald.craft.Recipe;
import me.quozul.fuald.items.ItemStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class WorkbenchesFrame extends JFrame {
    public WorkbenchesFrame() {
        this.setTitle("Workbenches");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(this.buildContentPane());
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    public JPanel buildContentPane() {
        JPanel panel = new JPanel();
        Dimension size = this.getSize();
        panel.setPreferredSize(new Dimension((int) size.getWidth() - 100, (int) size.getHeight() - 100));

        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(panel.getPreferredSize());

        for (Bench bench : Main.game.getBiome().getBenches()) {
            JPanel bench_panel = new JPanel();
            bench_panel.setLayout(new GridLayout());

            for (Recipe recipe : bench.getRecipes()) {
                JPanel recipe_panel = new JPanel();
                recipe_panel.setBorder(BorderFactory.createTitledBorder(recipe.getOutputItemStack().getItem().getName()));

                // create ingredient list
                JList ingredient_list = new JList();
                List<String> dlm = new ArrayList<>();
                for (ItemStack itemStack : recipe.getInputItemStack())
                    dlm.add(String.format("%d %s", itemStack.getAmount(), itemStack.getItem().getName()));
                ingredient_list.setListData(dlm.toArray());

                // craft button
                JButton craft_button = new JButton("Craft");
                craft_button.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        recipe.craft(Main.game.getPlayer().getInventory());
                    }
                });

                recipe_panel.add(ingredient_list);
                recipe_panel.add(craft_button);
                bench_panel.add(recipe_panel);
            }

            tabs.addTab(bench.getName(), bench_panel);
        }

        panel.add(tabs);

        return panel;
    }
}