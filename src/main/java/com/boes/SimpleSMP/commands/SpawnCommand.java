package com.boes.SimpleSMP.commands;

import com.boes.SimpleSMP.SimpleSMP;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("setspawn")) {
            if (!player.hasPermission("simplesmp.setspawn")) {
                player.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }

            Location loc = player.getLocation();
            SimpleSMP.getInstance().getConfig().set("spawn", loc);
            SimpleSMP.getInstance().saveConfig();
            player.sendMessage("§aSpawn location set.");
        }

        if (label.equalsIgnoreCase("spawn")) {
            if (SimpleSMP.getInstance().getConfig().contains("spawn")) {
                Location loc = SimpleSMP.getInstance().getConfig().getLocation("spawn");
                player.teleport(loc);
                player.sendMessage("§aTeleported to spawn.");
            } else {
                player.sendMessage("§cSpawn is not set yet.");
            }
        }

        return true;
    }
}
