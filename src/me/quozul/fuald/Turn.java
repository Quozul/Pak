package me.quozul.fuald;

import me.quozul.fuald.enums.ItemType;
import me.quozul.fuald.events.AttackEvent;
import me.quozul.fuald.events.DeathEvent;
import me.quozul.fuald.items.Inventory;
import me.quozul.fuald.items.Item;
import me.quozul.fuald.items.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Turn {
    // listener
    public List<DeathEvent> deathEventListener = new ArrayList<>();
    public void addDeathEventListener(DeathEvent listener) {
        deathEventListener.add(listener);
    }

    public List<AttackEvent> attackEventListener = new ArrayList<>();
    public void addAttackEventListener(AttackEvent listener) {
        attackEventListener.add(listener);
    }

    // turn logic
    private Entity ENTITY;

    /**
     * Create a new Turn with a player
     */
    public Turn(Biome biome) {
        List<Entity> entities = biome.getEntities();
        Entity randomEntity = entities.get(new Random().nextInt(entities.size()));
        this.ENTITY = randomEntity.clone();
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
    public void attack(Entity attacker, Entity victim, Item item) {
        // remove health from entity on attack
        if (item.getItemType() == ItemType.WEAPON)
            victim.addHealth(-item.getAttackDamage());
        else
            victim.addHealth(-1);

        for (AttackEvent listener : attackEventListener)
            listener.onDamageDealt(attacker, victim);

        if (victim.getHealth() <= 0) {
            // victim is dead
            if (attacker instanceof Player) {
                // attacker is a player
                Inventory lootedInventory = victim.getLootInventory().getLootedInventory();
                lootedInventory.moveTo(((Player) attacker).getCollectables());
            }

            for (DeathEvent listener : deathEventListener)
                listener.onEntityDie(attacker, victim);
        }
    }
}
