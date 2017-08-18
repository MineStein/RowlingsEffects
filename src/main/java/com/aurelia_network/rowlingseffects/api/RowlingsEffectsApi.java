package com.aurelia_network.rowlingseffects.api;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class RowlingsEffectsApi {

    private class CleanupTask extends BukkitRunnable {

        private RowlingsEffectsApi api;

        public RowlingsEffectsApi getApi() {
            return api;
        }

        public CleanupTask(RowlingsEffectsApi api) {
            this.api = api;
        }

        public void run() {

        }
    }

    private RowlingsEffectsPlugin plugin;
    private ConfigurationManager configurationManager;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public RowlingsEffectsApi(RowlingsEffectsPlugin plugin) {
        this.plugin = plugin;
        this.configurationManager = new ConfigurationManager(this);

        ConfigurationManager config = getConfigurationManager();

        Bukkit.getLogger().info("Loaded " + config.getEffects(Effect.EffectType.SKIN).size() + " wand skins!");
        Bukkit.getLogger().info("Loaded " + config.getEffects(Effect.EffectType.COLORS).size() + " wand colors!");
        Bukkit.getLogger().info("Loaded " + config.getEffects(Effect.EffectType.PATRONUSES).size() + " patronuses!");
        Bukkit.getLogger().info("Loaded " + config.getEffects(Effect.EffectType.HATS).size() + " hats!");

        if (config.doRegularCleanup()) {
            long delay = config.getRegularCleanupDelay();
            long interval = config.getRegularCleanupInterval();

            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new CleanupTask(this), delay, interval);

            Bukkit.getLogger().info("Enabled regular cleanup of effects.");
        }
    }
}
