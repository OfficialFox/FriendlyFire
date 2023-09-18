package com.foxdev.happyhour;

import com.foxdev.happyhour.Listener.FriendlyFireListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HappyHour extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new FriendlyFireListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
