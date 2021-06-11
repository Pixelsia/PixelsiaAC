package com.github.pixelsia.anticheat.ac;

import com.github.pixelsia.anticheat.ac.engine.ACEngine;
import org.bukkit.plugin.java.JavaPlugin;

public final class PixelsiaAC extends JavaPlugin {

    public static JavaPlugin instance = this;

    @Override
    public void onEnable() {
        Kei.out("ServerVersion: ", Kei.a(this));
        new ACEngine(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
