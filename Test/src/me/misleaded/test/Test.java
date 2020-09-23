package me.misleaded.test;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.test.commands.Commands;


public class Test extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin is enabled");
//		getServer().getPluginManager().registerEvents(new DoubleJumpEvents(), this);
		getCommand("output").setExecutor(new Commands());

	}
	
	@Override
	public void onDisable() {
		
	}
}
