package gg.fold.trollutils.commands;

        import org.bukkit.Bukkit;
        import org.bukkit.ChatColor;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;
        import org.bukkit.entity.Player;
        import gg.fold.Utils.DemoUtils;


public class DemoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("fold.demo")) {
            if (args.length == 1) {
                final Player t = Bukkit.getPlayerExact(args[0]);
                DemoUtils balls = new DemoUtils();
                balls.showMinecraftDemoScreenTo(t);
                p.sendMessage(ChatColor.YELLOW + "demoed the shit out of " + ChatColor.LIGHT_PURPLE + t.getName());
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /demo <player>");
            }
        } else {
            p.sendMessage(ChatColor.RED + "No permission");
        }
        return false;
    }
}