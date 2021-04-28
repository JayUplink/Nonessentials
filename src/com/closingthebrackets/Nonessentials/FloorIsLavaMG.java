package com.closingthebrackets.Nonessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FloorIsLavaMG implements CommandExecutor {
	
	private Main plugin;
	
	public FloorIsLavaMG(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!label.equalsIgnoreCase("floorislava")) {
			return true;
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (isAuthorised(player)) {
				// If player has perms:
				
				if (args.length > 0 && args.length < 3) {
					// Correct number of args for command
					boolean running = plugin.minigamefloorislava.isPlaying();
					
					if (args[0].equalsIgnoreCase("start")) {
						// Start
						if (running) {
							// Game already started
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lGame has already started!"));
						} else {
							// Start game
							Bukkit.broadcastMessage(plugin.getConfig().getString("floorislavaminigame.startbroadcastmessage"));
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lStarting &6Floor is Lava"));
							plugin.minigamefloorislava.start(args[1]);
						}
					} else if (args[0].equalsIgnoreCase("stop")) {
						// Stop
						if (running) {
							plugin.minigamefloorislava.stop();
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lEnded &6Floor is Lava"));
						} else {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lCan't stop game that never started."));
						}
					}
					
				} else {
					// Not enough/too many args
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4/floorislava <start|stop> [permisson]"));
				}
				
				
			} else {
				// No permission
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to execute this command."));
			}
		} else {
			sender.sendMessage("[Nonessentials] Command cannot be executed from console");
		}
		
		return true;
	}
	
	
	private boolean isAuthorised(Player player) {
		if (player.hasPermission("nonessentials.minigame.floorislava") || player.hasPermission("nonessentials.minigame.*")
				|| player.hasPermission("nonessentials.*")) {
			return true;
		}
		return false;
	}
}
