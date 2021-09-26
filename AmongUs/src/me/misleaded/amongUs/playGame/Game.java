package me.misleaded.amongUs.playGame;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import me.misleaded.amongUs.genGame.Renderer;
import me.misleaded.amongUs.genGame.RoomGen;
import me.misleaded.amongUs.AmongUs;

public class Game {
	private RoomGen map;
	private ArrayList<Player> players;
	private Player imposter;
	
	public Game(int x, int y, ArrayList<Player> p) {
		map = new RoomGen(128, 128, 4, new int[] {x, y});
		players = p;
		
		int[][] rooms = map.getRooms();

		for (Player player : players) {
			player.teleport(new Location(player.getWorld(), rooms[0][2]+rooms[0][0]/2, 201, rooms[0][3]+rooms[0][1]/2));
			map.drawMap(player.getWorld());
			
			int[] tl = map.getTL();
			int[][] mapRooms = map.getRooms();
			int[][][] mapCorridors = map.getCorridors();
			
			MapView mapView = Bukkit.createMap(player.getWorld());
			mapView.getRenderers().clear();
			mapView.addRenderer(new Renderer(tl, mapRooms, mapCorridors));
			
			ItemStack item = new ItemStack(Material.FILLED_MAP);
			MapMeta mapMeta = (MapMeta) item.getItemMeta();
			mapMeta.setMapView(mapView);
			item.setItemMeta(mapMeta);
			
			player.getInventory().clear();
			player.getInventory().setItemInOffHand(item);
		}
		
		imposter = players.get(new Random().nextInt(players.size()));
		imposter.getInventory().setItem(2, new ItemStack(Material.DIAMOND_SWORD));
		new ImposterRunnable(imposter).runTaskTimer(AmongUs.plugin, 20, 1);
	}

}
