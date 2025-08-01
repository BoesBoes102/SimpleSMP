package com.boes.SimpleSMP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("simplesmp.nick")) return true;
        if (args.length == 0) {
            player.setDisplayName(player.getName());
            player.sendMessage("§aReset your nickname.");
        } else {
            String nick = ChatColor.translateAlternateColorCodes('&', String.join(" ", args));
            player.setDisplayName(nick);
            player.sendMessage("§aNickname changed to " + nick);
        }
        return true;
    }
}
