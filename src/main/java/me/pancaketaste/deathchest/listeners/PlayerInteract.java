package me.pancaketaste.deathchest.listeners;

import me.pancaketaste.deathchest.managers.ChestData;
import me.pancaketaste.deathchest.managers.ChestManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block clickedBlock = e.getClickedBlock();

        if (clickedBlock == null || !(clickedBlock.getState() instanceof Chest)) {
            return;
        }

        Chest chest = (Chest) clickedBlock.getState();
        ChestData chestData = ChestManager.getChestData(chest);

        if (chestData != null) {
            // Check if the player who interacts is the owner
            if (chestData.getPlayer() != player) {
                player.sendMessage(ChatColor.RED + "You are not the owner of this chest.");
                e.setCancelled(true);
            } else {
                // Drop all items
                for (ItemStack item : chestData.getInventory()) {
                    if (item != null) {
                        clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(), item);
                    }
                }

                // Break the chest
                ChestManager.remove(chest);
                clickedBlock.setType(Material.AIR);

                player.sendMessage(ChatColor.GREEN + "You recovered the items.");
            }
        }
    }

}
