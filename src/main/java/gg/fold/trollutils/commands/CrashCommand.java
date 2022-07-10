package gg.fold.trollutils.commands;

import gg.fold.Utils.CrashUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;


public class CrashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Plugin plugin = getServer().getPluginManager().getPlugin("TrollUtils");
        FileConfiguration config = plugin.getConfig();
        if (p.hasPermission("fold.crash")) {
            if (args.length == 1) {
                final Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    CrashUtils.crashPlayer(sender, t);
                    String message = config.getString("crash.message").replace("{player}", t.getName()).replace("&", "§");
                    p.sendMessage(message);
                    for (Player e : Bukkit.getServer().getOnlinePlayers()) {
                        if (e.hasPermission("fold.trollnotify")) {
                            String bc = config.getString("crash.notify").replace("{player}", t.getName()).replace("&", "§").replace("{target}", p.getName());
                            e.sendMessage(bc);
                        }
                    }
                }
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /crash <player>");
            }
        } else {
            p.sendMessage(ChatColor.RED + "No permission");
        }
        return false;
    }
}