package me.pandaguy360.scoreboard.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class ScoreboardHandler {
	public static void changeIndScore(Player p) {
		Objective objective =  Bukkit.getScoreboardManager().getMainScoreboard().getObjective("IndividualScores");
		Score individualScore = objective.getScore(p.getDisplayName());
	}
}