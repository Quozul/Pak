package me.quozul.fuald.swingui;

import me.quozul.fuald.Game;
import me.quozul.fuald.Item;
import me.quozul.fuald.Main;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AttackButton extends JButton implements MouseListener {
    public AttackButton() {
        super("Attack");

        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Game game = Main.game;
        game.getTurn().kill(game.getPlayer(), game.getTurn().getEntity(), new Item("Sword"));
    }

    public void mouseEntered(MouseEvent event) { }

    public void mouseExited(MouseEvent event) { }

    public void mousePressed(MouseEvent event) { }

    public void mouseReleased(MouseEvent event) { }
}
