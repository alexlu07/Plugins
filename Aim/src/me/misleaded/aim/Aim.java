package me.misleaded.aim;

import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.aim.commands.Commands;

public class Aim extends JavaPlugin {
	public static JavaPlugin plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		getCommand("start").setExecutor(new Commands());

	}
	
	@Override
	public void onDisable() {
		
	}
}
