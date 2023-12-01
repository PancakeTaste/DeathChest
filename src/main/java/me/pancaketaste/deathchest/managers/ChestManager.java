package me.pancaketaste.deathchest.managers;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChestManager {
    private static final Map<Chest, ChestData> chestMap = new HashMap<>();

    public ChestManager(Chest chest, ArmorStand hologram, Player player, ItemStack[] inventory) {
        ChestData chestData = new ChestData(hologram, player, inventory);
        chestMap.put(chest, chestData);
    }

    public static ChestData getChestData(Chest chest) {
        return chestMap.get(chest);
    }

    public static void removeChest(Chest chest) {
        chestMap.remove(chest);
    }

    public static Chest getPlayerDeathChest(Player player) {
        for (Chest chest : chestMap.keySet()) {
            ChestData chestData = chestMap.get(chest);
            if (chestData.getPlayer().equals(player)) {
                return chest;
            }
        }
        return null; // Return null if no death chest is found for the player
    }

    public static List<Chest> getAllChests() {
        return new ArrayList<>(chestMap.keySet());
    }

    public static void destroyAllChests() {
        for (Chest chest : chestMap.keySet()) {
            ChestData chestData = chestMap.get(chest);

            // Drop all items
            for (ItemStack item : chestData.getInventory()) {
                if (item != null) {
                    chest.getWorld().dropItemNaturally(chest.getLocation(), item);
                }
            }

            // Break the chest
            ArmorStand hologram = chestData.getHologram();
            if (hologram != null) {
                hologram.remove();
            }

            // Remove chest from the map
            chestMap.remove(chest);

            // Set the chest block to air
            chest.getBlock().setType(Material.AIR);
        }
    }
}
