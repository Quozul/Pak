package me.quozul.fuald.swingui;

import me.quozul.fuald.Entity;
import me.quozul.fuald.Main;
import me.quozul.fuald.Turn;
import me.quozul.fuald.events.AttackEvent;
import me.quozul.fuald.events.DeathEvent;
import me.quozul.fuald.events.NewTurnEvent;

import javax.swing.*;

public class EntityPanel extends JPanel {
    public EntityPanel() {
        this.add(new EntityName());
        this.add(new EntityHealth());
    }
}

class EntityName extends JLabel implements NewTurnEvent, DeathEvent {
    public EntityName() {
        this.setText("No entity yet");
        Main.game.addNewTurnStartedListener(this);
        Main.game.addDeathEventListener(this);
    }

    @Override
    public void onNewTurnStarted(Turn turn) {
        this.setText(turn.getEntity().getName());
    }

    @Override
    public void onEntityDie(Entity killer, Entity victim) {
        this.setText("Dead " + victim.getName());
    }
}

class EntityHealth extends JLabel implements AttackEvent, NewTurnEvent {
    public EntityHealth() {
        this.setText("");
        Main.game.addAttackEventListener(this);
        Main.game.addNewTurnStartedListener(this);
    }

    @Override
    public void onDamageDealt(Entity attacker, Entity victim) {
        this.setText(String.format("%d/%d", victim.getHealth(), victim.getStartHealth()));
    }

    @Override
    public void onNewTurnStarted(Turn turn) {
        Entity ent = turn.getEntity();
        this.setText(String.format("%d/%d", ent.getHealth(), ent.getStartHealth()));
    }
}