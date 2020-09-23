package me.misleaded.forceWand;

import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.forceWand.commands.Commands;
import me.misleaded.forceWand.events.Events;
import me.misleaded.forceWand.items.Wand;

public class ForceWand extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Wand.init();
		getCommand("forcewand").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new Events(), this);

	}
	
	@Override
	public void onDisable() {
		
	}
}
