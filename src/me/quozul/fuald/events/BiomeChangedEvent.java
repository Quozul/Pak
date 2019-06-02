package me.quozul.fuald.events;

import me.quozul.fuald.Biome;

public interface BiomeChangedEvent {
    void onBiomeChanged(Biome new_biome);
}
