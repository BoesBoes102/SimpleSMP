package com.boes.SimpleSMP.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import com.boes.SimpleSMP.SimpleSMP;

public class ClearLagTask {

    private final int intervalTicks = 20 * 60 * 3; // every 5 minutes

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                int removed = 0;
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (entity instanceof Item) {
                            entity.remove();
                            removed++;
                        }
                    }
                }

                Bukkit.broadcastMessage("§e[ClearLag] §fRemoved §c" + removed + " §fdropped items.");
            }
        }.runTaskTimer(SimpleSMP.getInstance(), intervalTicks, intervalTicks);
    }
}
