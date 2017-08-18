package com.aurelia_network.rowlingseffects.api;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
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

    public List<Effect> getEffects(Effect.EffectType type) {
        List<Effect> effects = new ArrayList<Effect>();
        List<?> list = config.getList(type.getKey());

        for (Object obj : list) {
            if (obj instanceof Effect) effects.add((Effect) obj);
        }

        return effects;
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
