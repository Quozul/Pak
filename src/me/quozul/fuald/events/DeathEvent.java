package me.quozul.fuald.events;

import me.quozul.fuald.Entity;

public interface DeathEvent {
    void onEntityDie(Entity killer, Entity victim);
}
