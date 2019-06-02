package me.quozul.fuald.events;

import me.quozul.fuald.Entity;

public interface AttackEvent {
    void onDamageDealt(Entity attacker, Entity victim);
}
