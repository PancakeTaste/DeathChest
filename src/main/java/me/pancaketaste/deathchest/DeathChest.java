package me.pancaketaste.deathchest;

import me.pancaketaste.deathchest.commands.DeathCTPCommand;
import me.pancaketaste.deathchest.files.MessagesConfig;
import me.pancaketaste.deathchest.listeners.PlayerDeath;
import me.pancaketaste.deathchest.listeners.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class DeathChest extends JavaPlugin {

    private Database database;

    @Override
    public void onEnable() {
        // Connect to SQLite database
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            database = new Database(getDataFolder().getAbsolutePath() + "/sqlite.db");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database! " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }

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
        try {
            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
