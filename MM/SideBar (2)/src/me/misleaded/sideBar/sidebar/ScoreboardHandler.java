package me.misleaded.sideBar.sidebar;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class ScoreboardHandler {
	static HashMap<String, Score> indScore = new HashMap<String, Score>();
	public static void storeIndScore(Player p) {
		String name = p.getName();
		Objective objective =  Bukkit.getScoreboardManager().getMainScoreboard().getObjective("IndividualScores");
		Score individualScore = objective.getScore(name);
		indScore.put(name, individualScore);
	}

} 

