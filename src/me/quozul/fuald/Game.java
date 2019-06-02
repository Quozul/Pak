package me.quozul.fuald;

import me.quozul.fuald.enums.ItemType;
import me.quozul.fuald.events.AttackEvent;
import me.quozul.fuald.events.BiomeChangedEvent;
import me.quozul.fuald.events.DeathEvent;
import me.quozul.fuald.events.NewTurnEvent;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.Item;
import me.quozul.fuald.items.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements DeathEvent, AttackEvent{
    // listener
    public List<NewTurnEvent> listeners = new ArrayList<>();
    public void addNewTurnStartedListener(NewTurnEvent listener) {
        listeners.add(listener);
    }

    public List<DeathEvent> deathEventListener = new ArrayList<>();
    public void addDeathEventListener(DeathEvent listener) {
        deathEventListener.add(listener);
    }

    public List<AttackEvent> attackEventListener = new ArrayList<>();
    public void addAttackEventListener(AttackEvent listener) {
        attackEventListener.add(listener);
    }

    public List<BiomeChangedEvent> BiomeChangedEventListeners = new ArrayList<>();
    public void addBiomeChangedEventListener(BiomeChangedEvent listener) {
        BiomeChangedEventListeners.add(listener);
    }

    @Override
    public void onEntityDie(Entity killer, Entity victim) {
        for (DeathEvent deathEventListener : deathEventListener)
            deathEventListener.onEntityDie(killer, victim);
    }

    @Override
    public void onDamageDealt(Entity attacker, Entity victim) {
        for (AttackEvent attackEventListener : attackEventListener)
            attackEventListener.onDamageDealt(attacker, victim);
    }

    // game logic
    private Turn TURN;
    private Player PLAYER;
    private Biome BIOME;
    private List<Biome> BIOMES;

    public Game() {
        Item sword = new Item("Sword");
        sword.setMaxStack(1);
        sword.setType(ItemType.WEAPON);
        sword.setAttackDamage(3);

        Inventory player_inv = new Inventory(10, new ItemStack(sword, 1));

        PLAYER = new Player(player_inv);
    }

    public void nextTurn() {
        if (BIOME == null) {
            BIOME = BIOMES.get(new Random().nextInt(BIOMES.size()));

            for (BiomeChangedEvent listener : BiomeChangedEventListeners)
                listener.onBiomeChanged(BIOME);
        } else if (BIOME.getSize() < Math.random()) {
            BIOME = BIOMES.get(new Random().nextInt(BIOMES.size()));

            for (BiomeChangedEvent listener : BiomeChangedEventListeners)
                listener.onBiomeChanged(BIOME);
        }


        TURN = new Turn(BIOME);
        System.out.println("New turn started");
        this.getPlayer().getCollectables().empty();

        // register listeners
        TURN.addDeathEventListener(this);
        TURN.addAttackEventListener(this);

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

    public void registerBiomes(List<Biome> biomes) {
        BIOMES = biomes;
        for (Biome biome : biomes) {
            System.out.println(biome.getName());
        }
    }
}
