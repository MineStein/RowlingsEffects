package com.aurelia_network.rowlingseffects.inventory;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import com.aurelia_network.rowlingseffects.api.ConfigurationManager;
import com.aurelia_network.rowlingseffects.api.Effect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collection;
import java.util.List;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class EffectInventory {

    private RowlingsEffectsPlugin plugin;
    private Effect.EffectType type;
    private Player player;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public Effect.EffectType getType() {
        return type;
    }

    public Player getPlayer() {
        return player;
    }

    public EffectInventory(RowlingsEffectsPlugin plugin, Effect.EffectType type, Player player) {
        this.plugin = plugin;
        this.type = type;
        this.player = player;
    }

    public int resize(Collection<?> collection) {
        int size = 9;

        if (collection.size() > 9) size = 18;
        if (collection.size() > 18) size = 27;
        if (collection.size() > 27) size = 36;
        if (collection.size() > 36) size = 45;
        if (collection.size() > 45) size = 54;

        return size;
    }

    public Inventory getInventory() {
        ConfigurationManager config = plugin.getApi().getConfigurationManager();
        Inventory inventory;

        if (type.equals(Effect.EffectType.SKIN)) {
            List<Effect> skins = config.getEffects(Effect.EffectType.SKIN);

            inventory = Bukkit.createInventory(null, resize(skins), "Skins");

            for (Effect effect : skins) {
                inventory.addItem(effect.getItem(player));
            }
        } else if (type.equals(Effect.EffectType.COLORS)) {
            List<Effect> colors = config.getEffects(Effect.EffectType.COLORS);

            inventory = Bukkit.createInventory(null, resize(colors), "Colors");

            for (Effect effect : colors) {
                inventory.addItem(effect.getItem(player));
            }
        } else if (type.equals(Effect.EffectType.PATRONUSES)) {
            List<Effect> patronuses = config.getEffects(Effect.EffectType.PATRONUSES);

            inventory = Bukkit.createInventory(null, resize(patronuses), "Patronuses");

            for (Effect effect : patronuses) {
                inventory.addItem(effect.getItem(player));
            }
        } else {
            List<Effect> hats = config.getEffects(Effect.EffectType.HATS);

            inventory = Bukkit.createInventory(null, resize(hats), "Hats");

            for (Effect effect : hats) {
                inventory.addItem(effect.getItem(player));
            }
        }

        return inventory;
    }
}
