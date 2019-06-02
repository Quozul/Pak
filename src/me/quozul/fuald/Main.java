package me.quozul.fuald;

import me.quozul.fuald.swingui.JavaSwing;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static Game game;
    public static JavaSwing UI;


    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        System.out.println(Arrays.toString(args));

        Main.game = new Game();

        if (args.length > 0) {
            new LoadContent();
        }

        UI = new JavaSwing();
    }
}
