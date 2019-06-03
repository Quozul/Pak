package me.quozul.fuald;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// https://stackoverflow.com/questions/32205773/developing-application-with-plugin-support-in-java
public class LoadContent {
    public LoadContent() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        File[] jars = new File("plugins").listFiles();

        for (File file : jars) {
            URL[] urls = { new URL("jar:file:" + file.getPath() + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            // get plugin info
            InputStream in = cl.getResourceAsStream("/plugin.yml");
            InputStreamReader isr = new InputStreamReader(in);

            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            String[] result1 = result.split("\r?\n|\r");
            Map<String, String> plugin_info = new HashMap<>();

            for (String str : result1) {
                String[] str1 = str.split(": ");
                plugin_info.put(str1[0], str1[1]);
            }

            // run main class
            String className = plugin_info.get("main-class");
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