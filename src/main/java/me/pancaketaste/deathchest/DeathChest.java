package me.pancaketaste.deathchest;

import me.pancaketaste.deathchest.commands.DeathCTPCommand;
import me.pancaketaste.deathchest.files.MessagesConfig;
import me.pancaketaste.deathchest.listeners.PlayerDeath;
import me.pancaketaste.deathchest.listeners.PlayerInteract;
import me.pancaketaste.deathchest.managers.ChestManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathChest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Configs
        MessagesConfig.setup();
        // Defaults
        MessagesConfig.get().addDefault("death", "§fUpon your death, all your inventory items are now stored in a §eDeathChest§f. To reclaim them, head back to the same location.");
        MessagesConfig.get().addDefault("hologram-title", "§e§lDeathChest");
        MessagesConfig.get().addDefault("chest-unowned", "§cYou don't own this chest.");
        MessagesConfig.get().addDefault("chest-open", "§e§lCongrats! §r§fYou recovered the items.");
        MessagesConfig.get().addDefault("tp", "§aTeleported to your death chest!");
        MessagesConfig.get().addDefault("tp-no-chest", "§cYou don't have a death chest.");
        MessagesConfig.get().options().copyDefaults(true);
        MessagesConfig.save();

        // Events
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);

        // Commands
        getCommand("deathctp").setExecutor(new DeathCTPCommand()); // Teleport to death chest
    }

    @Override
    public void onDisable() {
        ChestManager.destroyAllChests();
    }

}
