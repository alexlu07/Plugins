package me.misleaded.sideBar.sidebar;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SidebarManager {
	
	private static String typeShown = "Hub";
	private static HashMap<Player, Scoreboard> scoreboardMap = new HashMap<Player, Scoreboard>();
	private static HashMap<String, HashMap<Integer, String>> instructionsMap = ScoreboardFactory.createInstructions();
		
	public static void addScoreboard(Player p) {
		if (scoreboardMap.containsKey(p)) {
			p.setScoreboard(scoreboardMap.get(p));
			return;
		}
		
		Scoreboard s = ScoreboardFactory.createScoreboard();
		p.setScoreboard(s);
		scoreboardMap.put(p, s);
	}

	public static void update(Player p) {
		Scoreboard s = scoreboardMap.get(p);
		HashMap<Integer, String> instructions = instructionsMap.get(typeShown);
		
		for (String reset : s.getEntries()) {
			s.resetScores(reset);
		}

		for (Map.Entry<Integer, String> add : instructions.entrySet()) {
			s.getObjective("Display").getScore(add.getValue()).setScore(add.getKey());
		}
	}
	
	public static void activate(Player p) {
		Scoreboard s = scoreboardMap.get(p);
		p.setScoreboard(s);
		s.getObjective("Display").setDisplaySlot(DisplaySlot.SIDEBAR);
		
	}
	
	public static void deactivate(Player p) {
		Scoreboard s = scoreboardMap.get(p);
		Scoreboard m = Bukkit.getScoreboardManager().getMainScoreboard();
		s.getObjective("Display").setDisplaySlot(null);
		p.setScoreboard(m);
	}

}
