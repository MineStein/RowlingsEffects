package com.aurelia_network.rowlingseffects;

import com.aurelia_network.rowlingseffects.api.Effect;
import com.aurelia_network.rowlingseffects.api.RowlingsEffectsApi;
import com.aurelia_network.rowlingseffects.command.EffectsCommand;
import com.aurelia_network.rowlingseffects.command.RowlingsEffectsCommand;
import com.aurelia_network.rowlingseffects.listener.InventoryListener;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class RowlingsEffectsPlugin extends JavaPlugin {

    private RowlingsEffectsPlugin plugin;
    private RowlingsEffectsApi api;
    private MagicAPI magicApi;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public RowlingsEffectsApi getApi() {
        return api;
    }

    public MagicAPI getMagicApi() {
        return magicApi;
    }

    @Override
    public void onEnable() {
        plugin = this;

        ConfigurationSerialization.registerClass(Effect.class);

        api = new RowlingsEffectsApi(this);
        magicApi = (MagicAPI) Bukkit.getPluginManager().getPlugin("Magic");

        getCommand("rowlingseffects").setExecutor(new RowlingsEffectsCommand(this));
        getCommand("effects").setExecutor(new EffectsCommand(this));

        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);
    }
}
