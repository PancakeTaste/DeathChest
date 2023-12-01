package me.pancaketaste.deathchest.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfig {
    private static File file;
    private static FileConfiguration messagesFile;

    // Finds or generates the messages config file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathChest").getDataFolder() + File.separator + "messages.yml");

        if (!file.exists()) {
            try {
                File dataFolder = file.getParentFile();

                // Create the necessary directories if they don't exist
                if (!dataFolder.exists()) {
                    dataFolder.mkdirs();
                }

                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        messagesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return messagesFile;
    }

    public static void save() {
        try {
            messagesFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
