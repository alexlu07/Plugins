package me.misleaded.tabList.tablist;

import java.io.*;
import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
//import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistManager {
	private static int eventNumber;
	
	private static String host = "pandaguy360";
	
//	private static HashMap<String, Player[]> teams = new HashMap<>();
	private static String[] entries = new String[] {"Bees", "Squids", "Spiders", "Wolves", "Pigs"};
	private static HashMap<Character, Integer> charWidth = new HashMap<>();
	private static HashMap<String, Integer> prefixWidth = new HashMap<>();
	
	static {
		
		prefixWidth.put("Bees", 8);
		prefixWidth.put("Squids", 8);
		prefixWidth.put("Spiders", 5);
		prefixWidth.put("Wolves", 9);
		prefixWidth.put("Pigs", 7);
		
		System.out.println("1");
		
		try {
			ClassLoader CLDR = TablistManager.class.getClassLoader();
		    InputStream mapFile = CLDR.getResourceAsStream("me/misleaded/tabList/tablist/charMap.txt");
		    
		    System.out.println("opened");
		    
//		    System.out.println(mapFile.read());
//		    mapFile.close();
//			File mapFile = new File(TablistManager.class.getResource("./charMap.txt").toURI());
			
//			InputStream mapFile = TablistManager.class.getResourceAsStream("me/misleaded/tabList/tablist/charMap.txt");

			
			Scanner scanner = new Scanner(mapFile);
			while (scanner.hasNextLine()) {
//				System.out.println("PLEASE");
			    StringTokenizer st = new StringTokenizer(scanner.nextLine());
			    char c = st.nextToken().charAt(0);
			    int w = Integer.parseInt(st.nextToken());
//			    System.out.println(c);
//			    System.out.println(w);
			    charWidth.put(c, w);
			  }
			
			scanner.close();
			mapFile.close();
			
			System.out.println("closed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("err");
			e.printStackTrace();
		}
//		InputStream mapFile = TablistManager.class.getClassLoader().getResourceAsStream("charMap.txt");


		
		
//		Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
//		
//		
//		for (String e: entries) {
//			Set<String> entityEntries;
//			if (e == "Spiders") {
//				entityEntries = mainScoreboard.getTeam("dark_red").getEntries();
//			} else {
//				entityEntries = mainScoreboard.getTeam(e).getEntries();
//			}
//
//			Player[] players = new Player[entityEntries.size()];
//			int i = 0;
//			for (String name: entityEntries) {
//				players[i] = Bukkit.getPlayer(name);
//				i++;
//			}
//			
//			teams.put(e, players);
//			
//		}

	}
	
	public static void setHeader(Player p) {
		String headerText = "" + 
				ChatColor.GOLD + ChatColor.BOLD + "Minecraft Madness " + eventNumber + "\n" + 
				ChatColor.BLUE + "Event Hosted by " + ChatColor.RED + host + "\n" +
				ChatColor.DARK_BLUE + ChatColor.STRIKETHROUGH + repeat(" ", 50) + "\n";
		
		String[][] scores = getTeamsInOrder();
		
		for (int i = 0; i < scores.length; i++) {
			headerText += formatTeam(i+1, scores[i][0], scores[i][1]);
		}
		
		headerText += "" + ChatColor.DARK_BLUE + ChatColor.STRIKETHROUGH + repeat(" ", 50) + repeat("\n", 500) + ".";
		
		p.setPlayerListHeader(headerText);
	}
	
	private static String[][] getTeamsInOrder() {
		Objective teamScores = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("teamScores");
		
		String[][] scores = new String[entries.length][2];		
		
		for (int i = 0; i < entries.length; i++) {
			scores[i][0] = entries[i];
			scores[i][1] = Integer.toString(teamScores.getScore(entries[i]).getScore());
		}
		
		Arrays.sort(scores, new Comparator<String[]>() {
		    public int compare(String[] a, String[] b) {
		        return Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
		    }
		});
		
		return scores;
	}
	
	private static String formatTeam(int i, String name, String score) {
		
		Team team;
		if (name == "Spiders") {
			team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("dark_red");

		} else {
			team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(name);

		}

		Set<String> entrySet = team.getEntries();
		String[] players = Arrays.copyOf(entrySet.toArray(), entrySet.size(), String[].class);
		
		int placeholders = 200;
		placeholders -= 6 + 2; // 6 for team number, 2 for period
		placeholders -= prefixWidth.get(name);
		
//		System.out.println(charWidth);
		
		for (int idx = 0; idx < name.length(); idx++) {
			placeholders -= charWidth.get(name.charAt(idx));
		}
		
		for (int idx = 0; idx < score.length(); idx++) {
			placeholders -= charWidth.get(score.charAt(idx));
		}
		
//		System.out.println(name);
//		System.out.println(placeholders);
		
		int threeChar = 0;
		if (placeholders % 2 == 1) {
			threeChar = 1;
			placeholders -= 3;
		}
		
		int twoChar = placeholders / 2;
		
//		System.out.println(threeChar);
//		System.out.println(twoChar);
		
//		System.out.println(team.getPrefix());
		
		String result = "" + ChatColor.RESET + ChatColor.GOLD + i + ". " + 
							 team.getColor() + team.getPrefix() + "´" + name + 
							 ChatColor.RESET + repeat("´", twoChar) + repeat("í", threeChar) + ChatColor.GOLD + score + "\n";
		result += team.getColor() + formatOffline(players[0]) + " " + team.getColor() + formatOffline(players[1]) + "\n";
		
		return result;
		
	}
	
	private static String formatOffline(String p) {
		return Bukkit.getPlayer(p) == null ? ChatColor.GRAY + p : p;
	}
	
	private static String repeat(String str, int times) {
		String result = "";
		for (int i = 0; i < times; i++) {
			result += str;
		}
		return result;
	}
	
	public static void setEventNumber(int i) {
		eventNumber = i;
	}
}
