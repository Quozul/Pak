package me.quozul.fuald;

import java.util.List;

public interface Plugin {
    void run();
    List<Biome> registerBiomes();
}
