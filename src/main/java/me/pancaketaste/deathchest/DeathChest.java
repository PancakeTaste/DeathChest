package me.pancaketaste.deathchest;

import me.pancaketaste.deathchest.commands.DeathCTPCommand;
import me.pancaketaste.deathchest.listeners.PlayerDeath;
import me.pancaketaste.deathchest.listeners.PlayerInteract;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathChest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Events
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);

        // Commands
        getCommand("deathctp").setExecutor(new DeathCTPCommand()); // Teleport to death chest
    }

}
