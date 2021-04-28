package com.closingthebrackets.Nonessentials.models;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.closingthebrackets.Nonessentials.Main;

public class MiniGameFloorIsLava {
	
	private boolean isPlaying;
	
	private String eventName;
	
	private int task;
	
	public MiniGameFloorIsLava() {
		this.setPlaying(false);
	}

	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void setEventName(String event) {
		this.eventName = event;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	
	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}
	
	public void start(String event) {
		setPlaying(true);
		setEventName(event);
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), 
		new Runnable() {
			@Override
			public void run() {
				// Repeating every 10 ticks
				for (Player player : Bukkit.getOnlinePlayers()) {
					// do something to player
					if (!player.hasPermission("event." + eventName) || player.hasPermission("nonessentials.admin")
							|| player.getGameMode() == GameMode.CREATIVE) {
						continue;
					}
				
					
					switch (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType()) {
					case BEDROCK:
						break;
					case AIR:
						break;
					default:
						player.damage(1);
						break;
					}
				}
			}
		}, 0, 10);
		
		setTask(task);
	}
	
	public void stop() {
		setPlaying(false);
		
		// Cancel runnable
		Bukkit.getScheduler().cancelTask(getTask());
	}
}
