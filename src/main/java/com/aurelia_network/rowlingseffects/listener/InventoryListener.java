package com.aurelia_network.rowlingseffects.listener;

import com.aurelia_network.rowlingseffects.RowlingsEffectsPlugin;
import com.aurelia_network.rowlingseffects.api.Effect;
import com.aurelia_network.rowlingseffects.inventory.EffectInventory;
import com.aurelia_network.rowlingseffects.inventory.EffectsInventory;
import com.aurelia_network.rowlingseffects.item.ItemUtility;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright (c) 2013-2017 Tyler Grissom
 */
public class InventoryListener implements Listener {

    private RowlingsEffectsPlugin plugin;

    public RowlingsEffectsPlugin getPlugin() {
        return plugin;
    }

    public InventoryListener(RowlingsEffectsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack itemStack = event.getCurrentItem();
        int slot = event.getSlot();

        if (itemStack == null) return;
        if (itemStack.getItemMeta() == null) return;
        if (itemStack.getItemMeta().getDisplayName() == null) return;

        if (inventory.getName().equals("Effects")) {
            event.setCancelled(true);

            if (ItemUtility.isSimilar(itemStack, "Wand Skins")) {
                if (plugin.getMagicApi().getMage(player).getActiveWand() == null) {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 1F, 1F);
                    player.closeInventory();
                    player.sendMessage("§6(§eEffects§6) §cYou must be holding a wand!");

                    return;
                }

                player.openInventory(new EffectInventory(plugin, Effect.EffectType.SKIN, player).getInventory());
            } else if (ItemUtility.isSimilar(itemStack, "Wand Colors")) {
                if (plugin.getMagicApi().getMage(player).getActiveWand() == null) {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 1F, 1F);
                    player.closeInventory();
                    player.sendMessage("§6(§eEffects§6) §cYou must be holding a wand!");

                    return;
                }

                player.openInventory(new EffectInventory(plugin, Effect.EffectType.COLORS, player).getInventory());
            } else if (ItemUtility.isSimilar(itemStack, "Patronuses")) {
                if (plugin.getMagicApi().getMage(player).getActiveWand() == null) {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 1F, 1F);
                    player.closeInventory();
                    player.sendMessage("§6(§eEffects§6) §cYou must be holding a wand!");

                    return;
                }

                player.openInventory(new EffectInventory(plugin, Effect.EffectType.PATRONUSES, player).getInventory());
            } else if (ItemUtility.isSimilar(itemStack, "Hats")) {
                player.openInventory(new EffectInventory(plugin, Effect.EffectType.HATS, player).getInventory());
            }
        } else if (inventory.getName().equals("Skins")) {
            event.setCancelled(true);

            if (ItemUtility.isSimilar(itemStack, "Back")) {
                player.openInventory(new EffectsInventory(plugin).getInventory());
            } else {
                Effect effect = plugin.getApi().getConfigurationManager().getEffects(Effect.EffectType.SKIN).get(slot);

                effect.playOut(player);
            }
        } else if (inventory.getName().equals("Colors")) {
            event.setCancelled(true);

            if (ItemUtility.isSimilar(itemStack, "Back")) {
                player.openInventory(new EffectsInventory(plugin).getInventory());
            } else {
                Effect effect = plugin.getApi().getConfigurationManager().getEffects(Effect.EffectType.COLORS).get(slot);

                effect.playOut(player);
            }
        } else if (inventory.getName().equals("Patronuses")) {
            event.setCancelled(true);

            if (ItemUtility.isSimilar(itemStack, "Back")) {
                player.openInventory(new EffectsInventory(plugin).getInventory());
            } else {
                Effect effect = plugin.getApi().getConfigurationManager().getEffects(Effect.EffectType.PATRONUSES).get(slot);

                effect.playOut(player);
            }
        } else if (inventory.getName().equals("Hats")) {
            event.setCancelled(true);

            if (ItemUtility.isSimilar(itemStack, "Back")) {
                player.openInventory(new EffectsInventory(plugin).getInventory());
            } else {
                Effect effect = plugin.getApi().getConfigurationManager().getEffects(Effect.EffectType.HATS).get(slot);

                effect.playOut(player);
            }
        }
    }
}
