package me.pancaketaste.deathchest.managers;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ChestManager {
    private static final Map<Chest, ChestData> chestMap = new HashMap<>();

    public ChestManager(Chest chest, Player player, ItemStack[] inventory) {
        ChestData chestData = new ChestData(player, inventory);
        chestMap.put(chest, chestData);
    }

    public static ChestData getChestData(Chest chest) {
        return chestMap.get(chest);
    }

    public static void remove(Chest chest) {
        chestMap.remove(chest);
    }

}
