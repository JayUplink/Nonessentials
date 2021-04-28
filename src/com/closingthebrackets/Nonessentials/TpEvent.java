package com.closingthebrackets.Nonessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpEvent implements CommandExecutor {
	static String TPEpermission = "nonessentials.tpevent";
	private boolean debug;
	
	private JavaPlugin plugin;
	
	public TpEvent(JavaPlugin plugin) {
		this.plugin = plugin;
		this.debug = this.plugin.getConfig().getBoolean("debug");
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("tpevent")) {
			if (this.debug) {
				Bukkit.getConsoleSender().sendMessage("got args amount " + String.valueOf(args.length));
				if (true) {
					for (int i = 0; i < args.length; i++) {
						Bukkit.getConsoleSender().sendMessage(args[i]);
					}
				
				}
			}
			if (sender instanceof Player) {
				if (this.debug) {
					Bukkit.getConsoleSender().sendMessage("Player sender");
				}
				
				// Cast sender to player:
				Player player = (Player) sender;
				boolean isAuthorised = player.hasPermission(TPEpermission);
				
				
				if (args.length < 1) {
					Bukkit.getServer().dispatchCommand(player, "help tpevent");
				}
				
				// Only 1 arg: target location is player sender
				if (args.length == 1 && isAuthorised) {

					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&a[Nonessentials] &r&aTeleporting all players to you with permission &b" + args[0]));
					teleportPlayers(args[0], player.getLocation());
					Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', "&l&a[Nonessentials] &r&aTeleported players to &c" + player.getDisplayName()), TPEpermission);
					return true;
					
					
				// Two args: target location is another online player
				} else if (args.length == 2 && isAuthorised) {
					if (isPlayerOnline(args[1])) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&a[Nonessentials] &r&aTeleporting players with permission &b" + args[0]));
						Player destplayer = Bukkit.getPlayer(args[1]);
						teleportPlayers(args[0], destplayer.getLocation());
						Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', "&l&a[Nonessentials] &r&aTeleported players to &c" + destplayer.getDisplayName()), TPEpermission);
						return true;
					} else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&4Player " + args[1] + " not found."));
						return true;
					}
				
				// Case for cords input
				} else if (args.length == 4 && isAuthorised) {
					if (this.debug) {
						Bukkit.getConsoleSender().sendMessage("tpe got 4 args");
					}
					// Check each arg 3-5
					for (int i = 1; i < 4; i++) {
						if (!isValidInteger(args[i])) {
							// If any are not a valid integer
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&4Invalid arguments for coordinate input."));
							return true;
						}
					}
					int xCord = Integer.parseInt(args[1]);
					int yCord = Integer.parseInt(args[2]);
					int zCord = Integer.parseInt(args[3]);
					// At this point assume coordinates are valid.
					Location destination = new Location(player.getWorld(), xCord, yCord, zCord);
					Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', "&l&a[Nonessentials] &r&aTeleported players to cordinates."), TPEpermission);
					teleportPlayers(args[0], destination);
					
					
				// No permission case
				} else if (!isAuthorised) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to execute this command."));
				}
			
				return true;
			} 
			// Sender is console
			if (this.debug) {
				Bukkit.getConsoleSender().sendMessage("Console sender");
			}
			if (args.length < 2) {
				sender.sendMessage("[Nonessentials] Console must use two arguments when executing this command.");
				return false;
				
			} else if (args.length == 5) {
				if (this.debug) {
					Bukkit.getConsoleSender().sendMessage("5 args for console sender");
				}
				for (int i = 1; i < 4; i++) {
					if (!isValidInteger(args[i])) {
						// If any are not a valid integer
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&4Invalid arguments for coordinate input."));
						return true;
					}
				}
				int xCord = Integer.parseInt(args[1]);
				int yCord = Integer.parseInt(args[2]);
				int zCord = Integer.parseInt(args[3]);
				// At this point assume coordinates are valid.
				Location destination = new Location(Bukkit.getWorld(args[4]), xCord, yCord, zCord);
				Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', "&l&a[Nonessentials] &r&aTeleported players to cordinates."), TPEpermission);
				teleportPlayers(args[0], destination);
				return true;
				
			} else {
				if (isPlayerOnline(args[1])) {
					// If player is online
					teleportPlayers(args[0], Bukkit.getPlayer(args[1]).getLocation());
					sender.sendMessage("[EventTP] Teleported players with " + args[0] + " to player " + args[1]);
				}
				sender.sendMessage("[EventTP] Player not found.");
				return true;
			}
		
		}
		return true;
	}
	
	public boolean isPlayerOnline(String username) {
		Player p = Bukkit.getPlayer(username);
		if (p != null) {
			return true;
		}
		return false;
	}
	
	public void teleportPlayers(String perm, Location destination) {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.hasPermission("event." + perm)) {
				if (player.isOp()) {
					// Skip opped players bc they always have permission
					continue;
				}
				player.teleport(destination);
				player.sendMessage(ChatColor.BOLD + "You were teleported to the event.");
			}
		}
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
