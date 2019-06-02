package me.quozul.fuald;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Biome {
    private String NAME = "";
    private float SIZE = 0.5f;
    private List<Entity> BIOME_ENTITIES = new ArrayList<>();
    //TODO add Biome elements (benches, ...)

    /**
     * Creates a new biome
     * @param name name of the biome
     * @param entities entities that spawns in the biome
     */
    public Biome(String name, int size, Entity... entities) {
        this.setName(name);
        this.registerEntity(entities);
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
