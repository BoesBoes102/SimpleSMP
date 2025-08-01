package com.boes.SimpleSMP;

import com.boes.SimpleSMP.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.boes.SimpleSMP.Listeners.*;
import com.boes.SimpleSMP.Tasks.ClearLagTask;
import com.boes.SimpleSMP.Util.DataManager;

public class SimpleSMP extends JavaPlugin {

    private static SimpleSMP instance;
    private static DataManager dataManager;

    public static SimpleSMP getInstance() {
        return instance;
    }

    public static DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Init data file
        dataManager = new DataManager(this);

        // Events
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        // Commands
        TabCompleter tabCompleter = new TabCompleter();

        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpaCommand());
        getCommand("tpdeny").setExecutor(new TpaCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("glow").setExecutor(new GlowCommand());
        getCommand("sethome").setExecutor(new HomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("setspawn").setExecutor(new SpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        getCommand("tpa").setTabCompleter(tabCompleter);
        getCommand("tpaccept").setTabCompleter(tabCompleter);
        getCommand("tpdeny").setTabCompleter(tabCompleter);
        getCommand("nick").setTabCompleter(tabCompleter);
        getCommand("glow").setTabCompleter(tabCompleter);
        getCommand("sethome").setTabCompleter(tabCompleter);
        getCommand("home").setTabCompleter(tabCompleter);
        getCommand("setspawn").setTabCompleter(tabCompleter);
        getCommand("spawn").setTabCompleter(tabCompleter);

        // Tasks
        new ClearLagTask().start();

        getLogger().info("SimpleSMP has been enabled.");
    }


    @Override
    public void onDisable() {
        dataManager.save();
        getLogger().info("SimpleSMP has been disabled.");
    }
}
