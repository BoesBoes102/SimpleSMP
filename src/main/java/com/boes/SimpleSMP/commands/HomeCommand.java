package com.boes.SimpleSMP.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.boes.SimpleSMP.SimpleSMP;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class HomeCommand implements CommandExecutor {
    private final Map<String, Location> homes = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;
        FileConfiguration config = SimpleSMP.getInstance().getConfig();

        if (label.equalsIgnoreCase("sethome")) {
            SimpleSMP.getDataManager().getConfig().set("homes." + player.getUniqueId(), player.getLocation());
            SimpleSMP.getDataManager().save();
            player.sendMessage("§aHome set!");

        } else if (label.equalsIgnoreCase("home")) {
            Object locObj = SimpleSMP.getDataManager().getConfig().get("homes." + player.getUniqueId());
            if (locObj instanceof Location location) {
                player.teleport(location);
                player.sendMessage("§aTeleported to home!");
            } else {
                player.sendMessage("§cYou don't have a home set.");
            }

        }
        return true;
    }
}
