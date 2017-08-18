package com.aurelia_network.rowlingseffects.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class ItemUtility {

    public static boolean isSimilar(ItemStack comparator, Material material, String name) {
        return (comparator.getType().equals(material)) && ChatColor.stripColor(comparator.getItemMeta().getDisplayName()).equalsIgnoreCase(ChatColor.stripColor(name));
    }

    public static boolean isSimilar(ItemStack comparator, String name) {
        return ChatColor.stripColor(comparator.getItemMeta().getDisplayName()).equalsIgnoreCase(ChatColor.stripColor(name));
    }
}
