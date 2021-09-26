package me.misleaded.amongUs.playGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Queues {
	private static ArrayList<Game> games = new ArrayList<Game>();
	private static HashMap<String, ArrayList<Player>> queues = new HashMap<String, ArrayList<Player>>();
	
	private static int x = 1000;
	private static int y = 1000;
	
	private static void createGame(String queueName) {
		games.add(new Game(x + games.size() * 1000, y + games.size() * 1000, queues.get(queueName)));
		queues.remove(queueName);

	}
	
	public static boolean createQueue(String queueName, Player p) {
		if (queueExists(queueName)) {
			return false;
		}
		queues.put(queueName, new ArrayList<Player>());
		queues.get(queueName).add(p);
		return true;
	}
	
	public static boolean joinQueue(Player p, String queueName) {
		int players = queues.get(queueName).size();
		
		if (inQueue(p, queueName)) {
			return false;
		}
		
		queues.get(queueName).add(p);
		players += 1;
		p.teleport(new Location(p.getWorld(), 0, 70, 0));
		if (players == 10) {
			createGame(queueName);
		}
		return true;
	}
	
	public static String leaveQueue(Player p) {
		if (!queued(p)) {
			return null;
		}
		
		String queueName = getQueue(p);
		if (queueName != null) {
			if (queues.get(queueName).remove(p)) {
				return queueName;
			}
		}
		return null;
		
	}
	
	public static boolean forceStart(String queueName) {
		if (Queues.queueExists(queueName)) {
			createGame(queueName);
			return true;
		
		}
		return false;
	}
	
	public static ArrayList<Player> getQueuedPlayers(String queueName) {
		return queues.get(queueName);
	}
	
	public static int getNumQueued(String queueName) {
		return queues.get(queueName).size();
	}
	
	public static boolean queueExists(String queueName) {
		return queues.containsKey(queueName);
	}
	
	public static boolean inQueue(Player p, String queueName) {
		return queues.get(queueName).contains(p);
	}
		
	public static boolean queued(Player p) {
		for (String queueName : queues.keySet()) {
			if (inQueue(p, queueName)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getQueue(Player p) {
		for (String queueName : queues.keySet()) {
			if (inQueue(p, queueName)) {
				return queueName;
			}
		}
		return null;
	}
		
	public static Set<String> getQueues() {
		return queues.keySet();
	}
}
