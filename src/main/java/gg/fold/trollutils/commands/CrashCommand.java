package gg.fold.trollutils.commands;

import gg.fold.Utils.CrashUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CrashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("fold.crash")) {
            if (args.length == 1) {
                final Player t = Bukkit.getPlayer(args[0]);
                CrashUtils.crashPlayer(sender, t);
                p.sendMessage(ChatColor.YELLOW + "crashed the shit out of " + ChatColor.LIGHT_PURPLE + t.getName() + " LOLOLOLOL");
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /crash <player>");
            }
        } else {
            p.sendMessage(ChatColor.RED + "No permission");
        }
        return false;
    }
}