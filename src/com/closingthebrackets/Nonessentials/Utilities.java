package com.closingthebrackets.Nonessentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Utilities implements CommandExecutor {
	
	private Main plugin;
	
	public Utilities(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!isAuthed(sender)) {
			sender.sendMessage("No permission.");
		}
		
		switch (args.length) {
		case 1:
			if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
				plugin.reloadConfig();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"[Nonessentials] &aReloaded config.yml"));
			}
		}
		return false;
	}
	
	
	private boolean isAuthed(CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("nonessentials.admin")) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

}
