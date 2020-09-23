package me.misleaded.doubleJump;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.misleaded.doubleJump.commands.DoubleJumpBootsCommands;
import me.misleaded.doubleJump.events.DoubleJumpEvents;
import me.misleaded.doubleJump.items.DoubleJumpBoots;

public class DoubleJump extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin is enabled");
		DoubleJumpBoots.init();
		getServer().getPluginManager().registerEvents(new DoubleJumpEvents(), this);
		getCommand("doublejumpboots").setExecutor(new DoubleJumpBootsCommands());

	}
	
	@Override
	public void onDisable() {
		
	}
}
