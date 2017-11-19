package com.aurelia_network.rowlingseffects.command;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class RowlingsEffectsCommand implements CommandExecutor {

    private RowlingsEffectsPlugin plugin;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public RowlingsEffectsCommand(RowlingsEffectsPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("rowlingseffects.reload")) {
            sender.sendMessage("§6(§eEffects§6) §cYou don't have permission!");

            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§6(§eEffects§6) §cUnknown subcommand. Did you mean to do §4/rowlingseffect reload§c?");
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                try {
                    plugin.reloadConfig();

                    sender.sendMessage("§6(§eEffects§6) §7Reloaded configs!");
                } catch (Exception exception) {
                    exception.printStackTrace();

                    sender.sendMessage("§6(§eEffects§6) §cAn error occurred while reloading the configs.");
                }
            } else {
                sender.sendMessage("§6(§eEffects§6) §cUnknown subcommand. Did you mean to do §4/rowlingseffect reload§c?");
            }
        }

        return true;
    }
}
