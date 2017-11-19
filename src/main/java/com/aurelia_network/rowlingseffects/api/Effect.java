package com.aurelia_network.rowlingseffects.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class Effect implements ConfigurationSerializable {

    public enum EffectType {

        SKIN("wand-skins"),

        COLORS("wand-colors"),

        PATRONUSES("patronuses"),

        HATS("hats");

        private String key;

        public String getKey() {
            return key;
        }

        EffectType(String key) {
            this.key = key;
        }
    }

    private String iconString, displayName, command;
    private Permission permission;
    private int data, damage;
    private ItemStack icon;

    public String getIconString() {
        return iconString;
    }

    public int getData() {
        return data;
    }

    public int getDamage() {
        return damage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCommand() {
        return command;
    }

    public Permission getPermission() {
        return permission;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public Effect(String iconString) {
        this.iconString = iconString;
    }

    public Effect(Map<String, Object> map) {
        this.iconString = (String) map.get("icon");
        this.data = (Integer) map.get("data");
        this.damage = (Integer) map.get("damage");
        this.displayName = (String) map.get("name");
        this.command = (String) map.get("command");
        this.permission = new Permission((String) map.get("permission"));
        this.icon = getItemIcon();
    }

    public Effect(String iconString, int data, int damage, String displayName, String command, Permission permission) {
        this.iconString = iconString;
        this.data = data;
        this.damage = damage;
        this.displayName = displayName;
        this.command = command;
        this.permission = permission;
        this.icon = getItemIcon();
    }

    public ItemStack getItemIcon() {
        Material material = Material.getMaterial(iconString.toUpperCase());

        if (material == null) {
            Bukkit.getLogger().severe("Uh oh!");

            return new ItemStack(Material.DIRT);
        }

        ItemStack itemStack = new ItemStack(material, 1, (short) damage); {
            itemStack.getData().setData((byte) data);
        }

        return itemStack;
    }

    public static Effect deserialize(Map<String, Object> map) {
        return new Effect((String) map.get("icon"), ((Integer) map.get("data")), ((Integer) map.get("damage")), (String) map.get("name"), (String) map.get("command"), new Permission((String) map.get("permission")));
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("icon", iconString);
        map.put("data", data);
        map.put("damage", damage);
        map.put("name", displayName);
        map.put("command", command);
        map.put("permission", permission.getName());

        return map;
    }

    public void playOut(Player player) {
        player.closeInventory();

        if (!player.hasPermission(getPermission())) {
            player.sendMessage("§6(§eEffects§6) §cYou do not own this effect. Purchase it at §4http://aurelia-network.com/store§c!");

            return;
        }

        player.sendMessage("§6(§eEffects§6) §7You changed your effect to §r" + ChatColor.translateAlternateColorCodes('&', getDisplayName()) + "§7!");

        String cmd = command.replaceAll("%player%", player.getName());

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }

    public ItemStack getItem(Player player) {
        ItemStack itemStack = getIcon(); {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setDisplayName("§3" + ChatColor.translateAlternateColorCodes('&', getDisplayName()));
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);

            List<String> lore = new ArrayList<String>();

            lore.add("");

            if (!player.hasPermission(permission)) {
                lore.add("§cYou do not own this effect! Purchase new effects at:");
                lore.add("  §4http://www.aurelia-network.com/store");
            } else {
                lore.add("§eClick §7to use");
            }

            meta.setLore(lore);

            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }
}
