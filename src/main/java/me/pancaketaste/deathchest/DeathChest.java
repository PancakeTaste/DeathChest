package me.pancaketaste.deathchest;

import me.pancaketaste.deathchest.commands.DeathChestsCommand;
import me.pancaketaste.deathchest.listeners.PlayerDeath;
import me.pancaketaste.deathchest.listeners.PlayerInteract;
import me.pancaketaste.deathchest.managers.ChestManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathChest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Events
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);

        // Commands
        getCommand("deathchests").setExecutor(new DeathChestsCommand());
    }

}
