package me.misleaded.chatColor.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Events implements Listener {
	
	@EventHandler
	public void playerChats(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String playerName = p.getName();
		String playerPrefix = "";
		ChatColor playerColor = ChatColor.WHITE;
		
		String msg = e.getMessage();
		
//		System.out.println(msg);
//		System.out.println(e.getFormat());
		
		Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
//		String[] teamNames = new String[] {"Squids", "dark_red", "Pigs", "Wolves", "Bees"};
//		String color;

		for (Team team: mainScoreboard.getTeams()) {
			if (team.hasEntry(playerName)) {
				playerPrefix = team.getPrefix();
				playerColor = team.getColor();
			}
		}
		
		e.setFormat(playerPrefix + playerColor + playerName + ChatColor.WHITE + ": " + playerColor + msg);
//		e.setMessage(msg);
		
				
	}


}