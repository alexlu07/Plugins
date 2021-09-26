package me.misleaded.amongUs;

import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.amongUs.commands.Commands;

public class AmongUs extends JavaPlugin {
	public static JavaPlugin plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		getCommand("genMap").setExecutor(new Commands());
		getCommand("join").setExecutor(new Commands());
		getCommand("leave").setExecutor(new Commands());
		getCommand("create").setExecutor(new Commands());
		getCommand("forcestart").setExecutor(new Commands());
	}
	
	@Override
	public void onDisable() {
		
	}
}
