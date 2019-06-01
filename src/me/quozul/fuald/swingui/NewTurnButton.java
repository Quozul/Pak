package me.quozul.fuald.swingui;

import me.quozul.fuald.Main;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewTurnButton extends JButton implements MouseListener {
    public NewTurnButton() {
        super("New turn");

        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        Main.game.nextTurn();
    }

    public void mouseEntered(MouseEvent event) { }

    public void mouseExited(MouseEvent event) { }

    public void mousePressed(MouseEvent event) { }

    public void mouseReleased(MouseEvent event) { }
}
