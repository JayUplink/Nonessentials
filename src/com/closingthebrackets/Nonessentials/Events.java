package com.closingthebrackets.Nonessentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Events implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean isAuthed = false;
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (isAuthorised(player)) {
				isAuthed = true;
			}
		} else {
			isAuthed = true;
		}
		
		if (isAuthed) {
			
			events(sender, args);
			
		} else {
			sender.sendMessage("[NE] No permission.");
		}
		
		return true;
	}
	
	private void events(CommandSender sender, String[] args) {
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("start")) {
				if (args[1].equalsIgnoreCase("prepare") || args[1].equalsIgnoreCase("meteor")) {
					runEvent(args[1].toLowerCase());
				} else {
					sender.sendMessage("No such event.");
				}
			} else if (args[1].equalsIgnoreCase("stop")) {
				// stop
			} else {
				sender.sendMessage("Must start or stop event.");
			}
		} else {
			sender.sendMessage("Not enough arguments.");
		}
	}
	
	private void runEvent(String eventname) {
		//Bukkit.getServer().
	}
	
	private boolean isAuthorised(Player player) {
		if (player.hasPermission("nonessentials.events")
				|| player.hasPermission("nonessentials.*")) {
			return true;
		}
		return false;
	}
}
