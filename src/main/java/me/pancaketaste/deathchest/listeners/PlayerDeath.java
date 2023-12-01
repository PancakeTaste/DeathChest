package me.pancaketaste.deathchest.listeners;

import me.pancaketaste.deathchest.managers.ChestManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Location location = player.getLocation();
        Inventory inventory = player.getInventory();

        // Don't create a chest if no items are in inventory
        if (isInventoryEmpty(inventory)) {
            return;
        }

        // Create a chest
        location.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) location.getBlock().getState();

        // Clear drops
        e.getDrops().clear();

        // Store the inventory
        new ChestManager(chest, player, inventory.getContents());
    }

    private boolean isInventoryEmpty(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }
}
