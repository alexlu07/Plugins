package me.misleaded.sideBar.sidebar;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class ScoreboardFactory {
	public static Scoreboard createScoreboard() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		// HUB SCOREBOARD
//		Objective hObjective = scoreboard.registerNewObjective("HubObjective", "dummy", "MM 5");
//		hObjective.getScore(" ").setScore(6);
//	    hObjective.getScore("Your Team:").setScore(5);
//	    hObjective.getScore("Team").setScore(4);
//	    hObjective.getScore("  ").setScore(3);
//	    hObjective.getScore("   ").setScore(2);
//	    hObjective.getScore("Your Score: ").setScore(1);
//	    hObjective.getScore("Your Lifetime Score: ").setScore(0);
		scoreboard.registerNewObjective("Display", "dummy", "" + ChatColor.GOLD + ChatColor.BOLD + "MC Madness 5");
		//HUB TEAMS
		scoreboard.registerNewTeam("playerTeam");
		scoreboard.registerNewTeam("indScoreTeam");
		scoreboard.registerNewTeam("ltScoreTeam");
		Team playerTeam = scoreboard.getTeam("playerTeam");
		Team indScoreTeam = scoreboard.getTeam("indScoreTeam");
		Team ltScoreTeam = scoreboard.getTeam("ltScoreTeam");
		String individualScore = null;
		playerTeam.setSuffix(" ");
		indScoreTeam.setSuffix(individualScore);
		ltScoreTeam.setSuffix("$l");
		playerTeam.addEntry("");
		indScoreTeam.addEntry("Your Score: ");
		ltScoreTeam.addEntry("Your Lifetime Score: ");

	    return scoreboard;

	}

	public static HashMap<String, HashMap<Integer, String>> createInstructions() {
		HashMap<String, HashMap<Integer, String>> instructions = new HashMap<String, HashMap<Integer, String>>();

		// HUB SCOREBOARD INSTRUCTIONS
		HashMap<Integer, String> hubInstructions = new HashMap<Integer, String>();
		hubInstructions.put(6, " ");
		hubInstructions.put(5, "" + ChatColor.BLUE + "Your Team:");
	    hubInstructions.put(4, "");
	    hubInstructions.put(3, "  ");
	    hubInstructions.put(2, "   ");
	    hubInstructions.put(1, "" + ChatColor.BLUE + "Your Score: " );
	    hubInstructions.put(0, "" + ChatColor.BLUE + "Your Lifetime Score: ");

	    instructions.put("Hub", hubInstructions);
	    
	    return instructions;
	}
}