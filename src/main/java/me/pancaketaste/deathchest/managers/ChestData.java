package me.pancaketaste.deathchest.managers;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestData {
    private final ArmorStand hologram;
    private final Player player;
    private final ItemStack[] inventory;

    public ChestData(ArmorStand hologram, Player player, ItemStack[] inventory) {
        this.hologram = hologram;
        this.player = player;
        this.inventory = inventory;
    }

    public ArmorStand getHologram() {
        return hologram;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }
}
