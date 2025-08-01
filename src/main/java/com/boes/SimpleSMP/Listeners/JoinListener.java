package com.boes.SimpleSMP.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.boes.SimpleSMP.SimpleSMP;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String msg = SimpleSMP.getInstance().getConfig().getString("join-message");
        e.setJoinMessage(msg != null ? msg.replace("%player%", e.getPlayer().getName()) : null);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String msg = SimpleSMP.getInstance().getConfig().getString("leave-message");
        e.setQuitMessage(msg != null ? msg.replace("%player%", e.getPlayer().getName()) : null);
    }
}
