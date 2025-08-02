package com.boes.SimpleSMP.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();

        switch (cmd) {
            case "tpa":
                if (args.length == 1) {
                    return Bukkit.getOnlinePlayers().stream()
                            .map(p -> p.getName())
                            .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                            .collect(Collectors.toList());
                }
                break;

            case "nick":
                if (args.length == 1) {
                    return List.of(sender.getName());
                }
                break;

            case "glow":
                if (args.length == 1) {
                    return Arrays.asList("on", "off").stream()
                            .filter(opt -> opt.startsWith(args[0].toLowerCase()))
                            .collect(Collectors.toList());
                }
                break;

            default:
                return new ArrayList<>();
        }

        return new ArrayList<>();
    }
}
