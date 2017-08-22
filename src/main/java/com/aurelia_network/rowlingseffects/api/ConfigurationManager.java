package com.aurelia_network.rowlingseffects.api;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class ConfigurationManager {

    private RowlingsEffectsApi api;
    private RowlingsEffectsPlugin plugin;
    private FileConfiguration config;

    public RowlingsEffectsApi getApi() {
        return api;
    }

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public ConfigurationManager(RowlingsEffectsApi api) {
        this.api = api;
        this.plugin = api.getPlugin();
        this.config = plugin.getConfig();
    }

    public List<Effect> getAllEffects() {
        List<Effect> allEffects = new ArrayList<Effect>();

        for (Effect.EffectType type : Effect.EffectType.values()) {
            allEffects.addAll(getEffects(type));
        }

        return allEffects;
    }

    public List<Effect> getEffects(Effect.EffectType type) {
        List<Effect> effects = new ArrayList<Effect>();

        if (config.getConfigurationSection(type.getKey()) == null) return null;

        for (String key : config.getConfigurationSection(type.getKey()).getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection(type.getKey() + "." + key);

            effects.add(Effect.deserialize(section.getValues(false)));
        }

        return effects;
    }

    public List<Effect> searchAllEffects(String display) {
        List<Effect> allEffects = getAllEffects();
        List<Effect> results = new ArrayList<Effect>();

        for (Effect effect : allEffects) {
            display = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', display));
            String comparator = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', effect.getDisplayName()));

            if (display.equals(comparator)) results.add(effect);
        }

        if (results.size() == 0) Bukkit.getLogger().warning("Returned 0 search results for '" + display + "'");
        if (results.size() > 1) Bukkit.getLogger().warning("Search under all effects for '" + display + "' returned " + results.size() + " results.");

        return results;
    }

    public List<Effect> searchEffects(Effect.EffectType type, String display) {
        List<Effect> effects = getEffects(type);
        List<Effect> results = new ArrayList<Effect>();

        for (Effect effect : effects) {
            display = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', display));
            String comparator = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', effect.getDisplayName()));

            if (display.equals(comparator)) results.add(effect);
        }

        if (results.size() == 0) Bukkit.getLogger().warning("Returned 0 search results for '" + display + "'");
        if (results.size() > 1) Bukkit.getLogger().warning("Search under " + type.toString() + " effects for '" + display + "' returned " + results.size() + " results.");

        return results;
    }

    public boolean doRegularCleanup() {
        return plugin.getConfig().getBoolean("settings.regular-cleanup.enabled", true);
    }

    public long getRegularCleanupDelay() {
        return plugin.getConfig().getLong("settings.regular-cleanup.interval", 0);
    }

    public long getRegularCleanupInterval() {
        return plugin.getConfig().getLong("settings.regular-cleanup.interval", 60);
    }
}
