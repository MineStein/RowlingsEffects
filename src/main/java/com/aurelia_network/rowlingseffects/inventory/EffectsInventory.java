package com.aurelia_network.rowlingseffects.inventory;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class EffectsInventory {

    private RowlingsEffectsPlugin plugin;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public EffectsInventory(RowlingsEffectsPlugin plugin) {
        this.plugin = plugin;
    }

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Effects");

        // 10, 12, 14, 16

        ItemStack skins = new ItemStack(Material.DIAMOND_HOE, 1, (byte) 70); {
            ItemMeta meta = skins.getItemMeta();

            meta.setDisplayName("§3Wand Skins");
            meta.setLore(Arrays.asList("", "§8Sets of unique wand looks that you can use.", "", "§eClick §7to view"));
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

            skins.setItemMeta(meta);
        }

        ItemStack colors = new ItemStack(Material.INK_SACK, 1, (byte) new Random().nextInt(15)); {
            ItemMeta meta = colors.getItemMeta();

            meta.setDisplayName("§3Wand Colors");
            meta.setLore(Arrays.asList("", "§8Colored particles that circle you whenever you", "§8wield your wand in your hand.", "", "§eClick §7to view"));

            colors.setItemMeta(meta);
        }

        ItemStack patronuses = new ItemStack(Material.MONSTER_EGG); {
            ItemMeta meta = patronuses.getItemMeta();

            meta.setDisplayName("§3Patronuses");
            meta.setLore(Arrays.asList("", "§8Patronuses to guard you whenever you cast", "§8the Patronus Charm.", "", "§eClick §7to view"));

            patronuses.setItemMeta(meta);
        }

        ItemStack hats = new ItemStack(Material.LEATHER_HELMET); {
            ItemMeta meta = hats.getItemMeta();

            meta.setDisplayName("§3Hats");
            meta.setLore(Arrays.asList("", "§8Hats to wear on your head.", "", "§eClick §7to view"));
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

            hats.setItemMeta(meta);
        }

        inventory.setItem(10, skins);
        inventory.setItem(12, colors);
        inventory.setItem(14, patronuses);
        inventory.setItem(16, hats);

        return inventory;
    }
}
