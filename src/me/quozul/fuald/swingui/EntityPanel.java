package me.quozul.fuald.swingui;

import me.quozul.fuald.Entity;
import me.quozul.fuald.Main;
import me.quozul.fuald.Turn;
import me.quozul.fuald.events.AttackEvent;
import me.quozul.fuald.events.NewTurnEvent;

import javax.swing.*;

public class EntityPanel extends JPanel {
    public EntityPanel() {
        this.add(new EntityName());
        this.add(new EntityHealth());
    }
}

class EntityName extends JLabel implements NewTurnEvent {
    public EntityName() {
        this.setText("No entity yet");
        Main.game.addNewTurnStartedListener(this);
    }

    @Override
    public void onNewTurnStarted(Turn turn) {
        this.setText(turn.getEntity().getName());
    }
}

class EntityHealth extends JLabel implements AttackEvent {
    public EntityHealth() {
        this.setText("");
    }

    @Override
    public void onDamageDealt(Entity attacker, Entity victim) {
        System.out.println(victim.getHealth());
    }
}