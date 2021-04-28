package com.closingthebrackets.Nonessentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.closingthebrackets.Nonessentials.models.Countdown;

public class Countdowncmd implements CommandExecutor {
	
	private JavaPlugin plugin;
	
	public Countdowncmd(JavaPlugin plugin) { this.plugin = plugin; }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		boolean hasPerm = true;
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("nonessentials.countdown")) {
				hasPerm = false;
			}
		}
		
		if (hasPerm) {
			if (args.length < 1) {
				sender.sendMessage("Specify countdown amount");
				return true;
			}
			if (!isValidInteger(args[0])) {
				sender.sendMessage("Countdown amount must be an integer.");
				return true;
			}
			
			
			plugin.getServer().broadcastMessage("Counting down...");
			new Countdown(this.plugin, Integer.parseInt(args[0])).runTaskTimer(this.plugin, 0, 20);
		}
		
		return true;
	}
	
	// Returns true if input is valid integer
	private boolean isValidInteger(String numberinput) {
		try {
			Integer.parseInt(numberinput);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
