package com.example.if2210_tb2_nge.plugin;

import javafx.scene.control.Tab;

public abstract class BasePlugin {
    protected Tab tab;

    public BasePlugin() {
        tab = new Tab();
    }
    abstract public void run();

}