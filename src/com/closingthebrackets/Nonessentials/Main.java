package com.closingthebrackets.Nonessentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.closingthebrackets.Nonessentials.models.MiniGameFloorIsLava;

public class Main extends JavaPlugin {
	
	public MiniGameFloorIsLava minigamefloorislava;

	@Override
	public void onEnable() {
		
		// Config Sheisse
		
		this.saveDefaultConfig();
		
		
		this.minigamefloorislava = new MiniGameFloorIsLava();
		
		
		
		// Set command executors
		if (this.getConfig().getBoolean("enabledcommands.tpevent")) {
			this.getCommand("tpevent").setExecutor(new TpEvent(this));
		}
		
		if (this.getConfig().getBoolean("enabledcommands.kitevent")) {
			this.getCommand("kitevent").setExecutor(new KitEvent());
		}
		
		
		if (this.getConfig().getBoolean("enabledcommands.floorislava")) {
			this.getCommand("floorislava").setExecutor(new FloorIsLavaMG(this));
		}
		
		
		this.getCommand("ne").setExecutor(new Utilities(this));
		
		if (this.getConfig().getBoolean("enabledcommands.countdown")) {
			this.getCommand("countdown").setExecutor(new Countdowncmd(this));
		}
		
		// Set listeners
		//this.getServer().getPluginManager().registerEvents(new FloorIsLavaMG(), this);
		
		
		Bukkit.getServer().getConsoleSender().sendMessage("[Nonessentials] Loaded Nonessentials by JayUplink.");
		Bukkit.getServer().getConsoleSender().sendMessage("[Nonessentials] For use by jonahwill.org or closingthebrackets.com networks only.");
		Bukkit.getServer().broadcast("Nonessentials version 1.2 loaded", "nonessentials.admin");
		Bukkit.broadcast("Nonessentials loaded & working, hopefully.", "nonessentials.admin");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("[Nonessentials] Disabling Nonessentials...");
	}
}
