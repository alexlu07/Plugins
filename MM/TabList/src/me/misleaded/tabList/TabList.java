package me.misleaded.tabList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.misleaded.tabList.commands.Commands;
import me.misleaded.tabList.tablist.TablistManager;

public class TabList extends JavaPlugin {
	@Override
	public void onEnable() {
		System.out.println("Custom Tablist Plugin Enabled");
		getCommand("setEventNumber").setExecutor(new Commands());
		
		TablistManager.setEventNumber(0);
		
		@SuppressWarnings("unused")
		BukkitTask task = new BukkitRunnable() {
		    public void run() {
				for (Player p: Bukkit.getServer().getOnlinePlayers()) {
					TablistManager.setHeader(p);
				}
		    }
		}.runTaskTimer(this, 0, 2);
		
	}
	@Override
	public void onDisable() {
		
	}
}
