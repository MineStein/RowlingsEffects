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
    private ItemStack icon;

    public String getIconString() {
        return iconString;
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
        this.displayName = (String) map.get("name");
        this.command = (String) map.get("command");
        this.permission = new Permission((String) map.get("permission"));

        String[] split = iconString.split(":");

        if (split.length > 0) {
            Material material = Material.getMaterial(split[0]);

            if (material != null) {
                if (split.length > 1) {
                    try {
                        byte parsedData = Byte.parseByte(split[1]);

                        this.icon = new ItemStack(material, 1, parsedData);

                        return;
                    } catch (NumberFormatException ignored) { }
                } else {
                    this.icon = new ItemStack(material);

                    return;
                }
            }
        }

        this.icon = new ItemStack(Material.DIRT);
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();

        if (iconString != null) map.put("icon", iconString);
        else if (icon != null) {
            map.put("icon", icon.getType().toString().toLowerCase() + ":" + icon.getData());
        }

        map.put("name", displayName);
        map.put("command", command);
        map.put("permission", permission.getName());

        return map;
    }

    public void playOut(Player player) {
        String cmd = command.replaceAll("%player%", player.getName());

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }

    public ItemStack getItem(Player player) {
        ItemStack itemStack = getIcon(); {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setDisplayName("§3" + ChatColor.translateAlternateColorCodes('&', getDisplayName()));
            meta.setUnbreakable(true);
            meta.getItemFlags().add(ItemFlag.HIDE_UNBREAKABLE);

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
