package com.example.if2210_tb2_nge.plugin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BasePlugin {
    @Getter
    @Setter
    private static List<Plugin> plugins;

    public String getPluginName() {
        return "Base Plugin";
    }
    public void addPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public void removePlugin(Plugin plugin) {
        plugins.remove(plugin);
    }

    public void removeAllPlugin() {
        plugins.clear();
    }

    private void run() {
        for (Plugin plugin : plugins) {
            plugin.run();
        }
    }


}
