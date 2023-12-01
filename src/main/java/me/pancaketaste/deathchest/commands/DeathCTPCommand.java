package me.pancaketaste.deathchest.commands;

import me.pancaketaste.deathchest.managers.ChestData;
import me.pancaketaste.deathchest.managers.ChestManager;
import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathCTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chest deathChest = ChestManager.getPlayerDeathChest(player);

            if (deathChest != null) {
                player.teleport(deathChest.getLocation().add(0.5, 0, 0.5));
                player.sendMessage(ChatColor.GREEN + "Teleported to your death chest!");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have a death chest.");
            }
            return true;
        } else {
            sender.sendMessage("This command can only be executed by players.");
        }
        return false;
    }
}
