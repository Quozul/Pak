package me.quozul.fuald;

import me.quozul.fuald.craft.Bench;
import me.quozul.fuald.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Biome {
    private String NAME = "";
    private float SIZE = 0.5f;
    private List<Entity> BIOME_ENTITIES = new ArrayList<>();
    private List<Bench> WORKBENCHES = new ArrayList<>();
    private List<Item> ITEMS = new ArrayList<>();

    /**
     * Creates a new biome
     * @param name name of the biome
     */
    public Biome(String name, int size) {
        this.setName(name);
        this.setSize(size);
    }

    /**
     * Creates a new blank biome
     */
    public Biome() { }

    /**
     * Get the name of the biome
     * @return
     */
    public String getName() {
        return NAME;
    }

    /**
     * Sets a new name for the biome
     * @param name
     */
    public void setName(String name) {
        NAME = name;
    }

    /**
     * Add entities that spawns in the biome
     * @param entities
     */
    public void registerEntity(Entity... entities) {
        BIOME_ENTITIES.addAll(Arrays.asList(entities));
    }

    /**
     * Add benches that are present in the biome
     * @param benches
     */
    public void registerBenches(Bench... benches) {
        WORKBENCHES.addAll(Arrays.asList(benches));
    }

    /**
     * Get a list of all workbenches of the biome
     * @return
     */
    public List<Bench> getBenches() {
        return this.WORKBENCHES;
    }

    /**
     * Add items that can be collected in the biome
     * @param items
     */
    public void addCollectables(Item... items) {
        ITEMS.addAll(Arrays.asList(items));
    }

    public Item getRandomCollectable() {
        return this.ITEMS.get(new Random().nextInt(this.ITEMS.size()));
    }

    public List<Entity> getEntities() {
        return this.BIOME_ENTITIES;
    }

    public void setSize(float size) {
        this.SIZE = size;
    }

    public float getSize() {
        return this.SIZE;
    }
}
