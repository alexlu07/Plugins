package me.misleaded.sideBar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.misleaded.sideBar.events.Events;
import me.misleaded.sideBar.sidebar.SidebarManager;
import me.misleaded.sideBar.commands.Commands;

public class SideBar extends JavaPlugin {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		Commands cmdExecutor = new Commands();
		getCommand("activateSidebar").setExecutor(cmdExecutor);
		getCommand("deactivateSidebar").setExecutor(cmdExecutor);
		@SuppressWarnings("unused")
		BukkitTask task = new BukkitRunnable() {
			public void run() {
				for (Player p: Bukkit.getServer().getOnlinePlayers()) {
					SidebarManager.update(p);
				}
			}
		}.runTaskTimer(this, 0, 2);
	}

/* MM X
 * 
 * Your Team:
 * Team
 * 
 * 
 * Your Score: Score Here
 * Your Lifetime Score: Lifetime Score Here
 * 
 * Individual score, Player Team(prefix, color, name), X, Lifetime Score(N/A)
 * 
 */

	@Override 
	public void onDisable() {
	
	}
}