package me.misleaded.amongUs;

import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.amongUs.commands.Commands;

public class AmongUs extends JavaPlugin {
	@Override
	public void onEnable() {
		getCommand("genMap").setExecutor(new Commands());

	}
	
	@Override
	public void onDisable() {
		
	}
}
