package com.boes.SimpleSMP.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GlowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("simplesmp.glow")) return true;

        boolean glowing = player.isGlowing();
        player.setGlowing(!glowing);
        player.sendMessage("§aGlow " + (!glowing ? "enabled" : "disabled") + "!");
        return true;
    }
}
