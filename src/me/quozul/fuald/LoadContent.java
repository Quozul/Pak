package me.quozul.fuald;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

// https://stackoverflow.com/questions/32205773/developing-application-with-plugin-support-in-java
public class LoadContent {
    public LoadContent() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        File[] jars = new File("plugins").listFiles();

        for (File file : jars) {
            URL[] urls = { new URL("jar:file:" + file.getPath() + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            // -6 because of .class
            String className = "Main";
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);

            if (Plugin.class.isAssignableFrom(c)) {
                Plugin plugin = (Plugin) c.newInstance();
                // And then, do something with the plugin here
                plugin.run();
                Main.game.registerBiomes(plugin.registerBiomes());
            }
        }
    }
}