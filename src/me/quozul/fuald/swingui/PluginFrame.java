package me.quozul.fuald.swingui;

import me.quozul.fuald.Main;
import me.quozul.fuald.Plugin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class PluginFrame extends JDialog {
    final PluginFrame frame = this;
    File[] jars = new File("plugins").listFiles();

    public PluginFrame() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle("Plugins");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(this.buildContentPane());
        this.setAlwaysOnTop(true);
        this.setModal(true);
        this.setVisible(true);
    }

    public JPanel buildContentPane() throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // https://stackoverflow.com/questions/32205773/developing-application-with-plugin-support-in-java
        for (File file : jars) {
            JPanel plugin_panel = new JPanel();
            plugin_panel.setLayout(new BoxLayout(plugin_panel, BoxLayout.X_AXIS));

            URL[] urls = { new URL("jar:file:" + file.getPath() + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);

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

            JLabel plugin_name = new JLabel(String.format("%s by %s", plugin_info.get("name"), plugin_info.get("author")));
            JButton load_plugin = new JButton("Load");
            load_plugin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String className = plugin_info.get("main-class");
                        className = className.replace('/', '.');
                        Class c = cl.loadClass(className);

                        if (Plugin.class.isAssignableFrom(c)) {
                            Plugin plugin = (Plugin) c.newInstance();
                            // And then, do something with the plugin here
                            plugin.run();
                            Main.game.registerBiomes(plugin.registerBiomes());
                        }

                        frame.dispose();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            plugin_panel.add(plugin_name);
            plugin_panel.add(load_plugin);

            panel.add(plugin_panel);
        }

        return panel;
    }
}