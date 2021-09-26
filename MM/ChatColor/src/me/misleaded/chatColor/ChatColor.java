package me.misleaded.chatColor;

import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.chatColor.events.Events;


public class ChatColor extends JavaPlugin{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}