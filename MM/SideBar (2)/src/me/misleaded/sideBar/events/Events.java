package me.misleaded.sideBar.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.misleaded.sideBar.sidebar.SidebarManager;

public class Events implements Listener {

	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p;
		p = e.getPlayer();
		SidebarManager.addScoreboard(p);
	    SidebarManager.activate(p);
	}
}