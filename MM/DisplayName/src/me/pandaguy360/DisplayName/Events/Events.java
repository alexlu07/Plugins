package me.pandaguy360.DisplayName.Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Events implements Listener {
	@EventHandler
	public void playerJoins(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String playerName = p.getName();
		String playerPrefix = "";
		ChatColor playerColor = ChatColor.WHITE;
		Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		for (Team team: mainScoreboard.getTeams()) {
			if (team.hasEntry(playerName)) {
				playerPrefix = team.getPrefix();
				playerColor = team.getColor();
			}
		p.setPlayerListName(playerPrefix + playerColor + " " + playerName);	
		}
	}
}

