package com.boes.SimpleSMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import com.boes.SimpleSMP.SimpleSMP;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TpaCommand implements CommandExecutor {
    private static final Map<UUID, UUID> requests = new HashMap<>();
    private static final Map<UUID, Long> timeouts = new HashMap<>();
    private final long timeoutSeconds = SimpleSMP.getInstance().getConfig().getLong("tpa.request-timeout", 60);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (label.equalsIgnoreCase("tpa")) {
            if (args.length != 1) return false;
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null || target.equals(player)) {
                player.sendMessage("§cPlayer not found.");
                return true;
            }
            requests.put(target.getUniqueId(), player.getUniqueId());
            timeouts.put(target.getUniqueId(), System.currentTimeMillis());
            player.sendMessage("§aTPA request sent to " + target.getName());
            target.sendMessage("§e" + player.getName() + " has requested to teleport to you. Use /tpaccept or /tpdeny.");
        }

        if (label.equalsIgnoreCase("tpaccept")) {
            UUID requesterId = requests.remove(player.getUniqueId());
            if (requesterId == null) {
                player.sendMessage("§cNo pending request.");
                return true;
            }

            long sentTime = timeouts.getOrDefault(player.getUniqueId(), 0L);
            if (System.currentTimeMillis() - sentTime > timeoutSeconds * 1000) {
                player.sendMessage("§cRequest timed out.");
                return true;
            }

            Player requester = Bukkit.getPlayer(requesterId);
            if (requester != null) requester.teleport(player);
            player.sendMessage("§aTeleport accepted.");
        }

        if (label.equalsIgnoreCase("tpdeny")) {
            if (requests.remove(player.getUniqueId()) != null) {
                player.sendMessage("§cTeleport request denied.");
            } else {
                player.sendMessage("§cNo pending request.");
            }
        }

        return true;
    }
}
