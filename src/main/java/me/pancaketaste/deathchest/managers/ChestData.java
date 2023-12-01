package me.pancaketaste.deathchest.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestData {
    private final Player player;
    private final ItemStack[] inventory;

    public ChestData(Player player, ItemStack[] inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }
}
