package me.quozul.fuald;

import me.quozul.fuald.events.DeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Turn {
    // listener
    public List<DeathEvent> listeners = new ArrayList<>();

    public void addDeathEventListener(DeathEvent listener) {
        listeners.add(listener);
    }

    // turn logic
    private Entity ENTITY;

    /**
     * Create a new Turn with a player
     */
    public Turn(Biome biome) {
        List<Entity> entities = biome.getEntities();
        ENTITY = entities.get(new Random().nextInt(entities.size()));
    }

    /**
     * Get the current entity of the turn
     * @return entity
     */
    public Entity getEntity() {
        return this.ENTITY;
    }

    /**
     * Create an attack
     * @param attacker the entity that attacks
     * @param victim the entity attacked
     * @param item the item used by the attacker to attack the victim
     */
    public void kill(Entity attacker, Entity victim, Item item) {
        System.out.println("Player attacked entity with item");

        System.out.println("entity's inventory" + ENTITY.getInventory().getItemStack(0).getAmount());

        for (ItemStack itemStack : victim.getInventory().getItemStacks()) {
            System.out.println(itemStack.getAmount());
            attacker.getInventory().addItemStacks(itemStack);
        }
    }
}
