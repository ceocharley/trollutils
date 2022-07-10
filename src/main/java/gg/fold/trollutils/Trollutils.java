package gg.fold.trollutils;

import gg.fold.trollutils.commands.CrashCommand;
import gg.fold.trollutils.commands.DemoCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Trollutils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("crash").setExecutor(new CrashCommand());
        getCommand("demo").setExecutor(new DemoCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
