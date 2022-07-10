package gg.fold.trollutils;

import gg.fold.trollutils.commands.CrashCommand;
import gg.fold.trollutils.commands.DemoCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Trollutils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        long startTime = System.nanoTime();
        getServer().getConsoleSender().sendMessage("Loading " + getName() + " version " +
                getDescription().getVersion() + " by " + getDescription().getAuthors());
        getCommand("crash").setExecutor(new CrashCommand());
        getCommand("demo").setExecutor(new DemoCommand());
        saveDefaultConfig();
        long elapsedTime = System.nanoTime() - startTime;
        getServer().getConsoleSender().sendMessage("Enabled " + getName() + " in " + elapsedTime/1000000 + "ms");
    }

    @Override
    public void onDisable() {
        long startTime = System.nanoTime();
        long elapsedTime = System.nanoTime() - startTime;
        getServer().getConsoleSender().sendMessage("Disabled " + getName() + " in " + elapsedTime/1000000 + "ms");
    }
}
