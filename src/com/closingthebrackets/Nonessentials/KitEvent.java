package com.closingthebrackets.Nonessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class KitEvent implements CommandExecutor {
	private String permission = "nonessentials.kitevent";
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "help kitevent");
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission(permission)) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error: No pemission."));
			}
		}
		
		if (args.length != 2) {
			Bukkit.getServer().dispatchCommand(sender, "help kitevent");
		} else {
			String eventname = args[0];
			String kitname = args[1];
			
			int playersEffected = kitPlayers(eventname, kitname);
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "[Nonessentials] &l&aGave " + String.valueOf(playersEffected) + " player(s) kit: &r" + kitname));
		}

		return true;
	}
	
	
	
	public int kitPlayers(String perm, String kitname) {
		int count = 0;
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.hasPermission("event." + perm) && !player.isOp()) {
				count++;
				player.sendMessage(ChatColor.BOLD + "Here is your event kit:");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kit " + kitname + " " + player.getDisplayName());
			}
		}
		return count;
	}
}
