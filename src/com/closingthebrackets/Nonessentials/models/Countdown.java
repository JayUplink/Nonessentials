package com.closingthebrackets.Nonessentials.models;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;


public class Countdown extends BukkitRunnable {
	
	private final JavaPlugin plugin;
	
	private int count;
	
	public Countdown(JavaPlugin plugin, int count) {
		this.plugin = plugin;
		this.count = count;
	}
	
	@Override
	public void run() {
		if (count > 0) {
			plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "  &a&l" + String.valueOf(count)));
			playSound(1.0f);
			sendTitle(String.valueOf(count));
			count--;
		} else {
			plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "  &a&lGO!"));
			sendTitle("GO!");
			playSound(2.0f);
			this.cancel();
		}
	}
	
	public void playSound(float pitch) {
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void sendTitle(String text) {
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			// Sendtitle is deprecated to WHAT THEN???
			player.sendTitle(text, null);
		}
	}
}
