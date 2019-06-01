package me.quozul.fuald.events;

import me.quozul.fuald.Turn;

// https://www.youtube.com/watch?v=T7c6lzbeFHE
public interface NewTurnEvent {
    void onNewTurnStarted(Turn turn);
}
