package me.quozul.fuald.swingui;

import me.quozul.fuald.items.ItemStack;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ItemFrame extends JDialog {
    final ItemFrame frame = this;
    public ItemFrame(ItemStack itemStack) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle(itemStack.getItem().getName() + " informations");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(this.buildContentPane(itemStack));
        this.setAlwaysOnTop(true);
        this.setVisible(true);

        frame.addFocusListener( new FocusListener() {
            private boolean gained = false;
            @Override
            public void focusGained( FocusEvent e ) {
                gained = true;
            }

            @Override
            public void focusLost( FocusEvent e ) {
                if ( gained ){
                    frame.dispose();
                }
            }
        });
    }

    public JPanel buildContentPane(ItemStack itemStack) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel(String.format("<html><b>Name</b>: %s</html>", itemStack.getItem().getName())));
        panel.add(new JLabel(String.format("<html><b>Amount</b>: %s</html>", itemStack.getAmount())));

        String type;
        switch (itemStack.getItem().getItemType()) {
            case WEAPON:
                type = "Weapon";
                break;
            case EATABLE:
                type = "Food";
                break;
            case POTION:
                type = "Potion";
                break;
            default:
                type = "Other (sell, crafting ingredient, ...)";
        }

        panel.add(new JLabel(String.format("<html><b>Usage</b>: %s</html>", type)));

        String description = "<html>";
        switch (itemStack.getItem().getItemType()) {
            case WEAPON:
                description = description + "<br><b>Attack damage</b>: " + itemStack.getItem().getAttackDamage();
                break;
            case EATABLE:
                description = description + String.format("<br><b>Health restoration</b>: %d", 0);
                break;
            case POTION:
                description = description + String.format("<br><b>Effect</b>: %s<br><b>Duration</b>: %d turn", "none", 0);
                break;
            default:
                description = description + "<br><b>Sell value</b>: 0 gold";
        }

        description = description + "</html>";
        panel.add(new JLabel(description));

        return panel;
    }
}
