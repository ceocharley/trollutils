package gg.fold.trollutils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import gg.fold.Utils.DemoUtils;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;


public class DemoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Plugin plugin = getServer().getPluginManager().getPlugin("TrollUtils");
        FileConfiguration config = plugin.getConfig();
        if (config.getString("demo.enabled").equals("true")) {
            if (p.hasPermission("fold.demo")) {
                if (args.length == 1) {
                    final Player t = Bukkit.getPlayerExact(args[0]);
                    if (t != null) {
                        DemoUtils balls = new DemoUtils();
                        balls.showMinecraftDemoScreenTo(t);
                        String message = config.getString("demo.message").replace("{player}", t.getName()).replace("&", "§");
                        p.sendMessage(message);
                        for (Player e : Bukkit.getServer().getOnlinePlayers()) {
                            if (e.hasPermission("fold.trollnotify")) {
                                String bc = config.getString("demo.notify").replace("{player}", t.getName()).replace("&", "§").replace("{target}", p.getName());
                                e.sendMessage(bc);
                            }
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Usage: /demo <player>");
                }
            } else {
                p.sendMessage(ChatColor.RED + "No permission");
            }
        }
        return false;
    }
}