package me.quozul.fuald;

import me.quozul.fuald.enums.ItemType;
import me.quozul.fuald.events.NewTurnEvent;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.Item;
import me.quozul.fuald.items.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    // listener
    public List<NewTurnEvent> listeners = new ArrayList<>();

    public void addNewTurnStartedListener(NewTurnEvent listener) {
        listeners.add(listener);
    }

    // game logic
    private Turn TURN;
    private Player PLAYER;
    private Biome BIOME;

    public Game(Biome biome) {
        BIOME = biome;

        Item sword = new Item("Sword");
        sword.setMaxStack(1);
        sword.setType(ItemType.WEAPON);
        sword.setAttackDamage(3);

        Inventory player_inv = new Inventory(10, new ItemStack(sword, 1));

        PLAYER = new Player(player_inv);
    }

    public void nextTurn() {
        TURN = new Turn(BIOME);
        System.out.println("New turn started");
        this.getPlayer().getCollectables().empty();

        // register listeners
        TURN.addDeathEventListener(Main.UI);

        for (NewTurnEvent listener : listeners)
            listener.onNewTurnStarted(TURN);
    }

    public Turn getTurn() {
        return TURN;
    }

    public Player getPlayer() {
        return PLAYER;
    }

    public Biome getBiome() {
        return BIOME;
    }

    public void setBiome(Biome biome) {
        BIOME = biome;
    }
}
