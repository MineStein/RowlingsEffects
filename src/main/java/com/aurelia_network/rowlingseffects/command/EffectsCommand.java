package com.aurelia_network.rowlingseffects.command;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import com.aurelia_network.rowlingseffects.inventory.EffectsInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class EffectsCommand implements CommandExecutor {

    private RowlingsEffectsPlugin plugin;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public EffectsCommand(RowlingsEffectsPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        player.openInventory(new EffectsInventory(plugin).getInventory());

        return true;
    }
}
