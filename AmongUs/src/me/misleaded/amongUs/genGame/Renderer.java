package me.misleaded.amongUs.genGame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class Renderer extends MapRenderer {
	private int[] topLeft;
	private int[][] rooms;
	private int[][][] corridors;
	
	public Renderer(int[] tl, int[][] r, int[][][] c) {
		topLeft = tl;
		rooms = r;
		corridors = c;
	}
	
	@Override
	public void render(MapView mapView, MapCanvas mapCanvas, Player p) {
		
		for (int i = 0; i < 128; i++) {
			for (int j = 0; j < 128; j++) {
				mapCanvas.setPixel(i, j, (byte) 34);
			}
		}
				
		for (int[] room : rooms) {
			for (int w = 0; w < room[0]; w++) {
				for (int h = 0; h < room[1]; h++) {
					mapCanvas.setPixel(room[2] - topLeft[0] + w, room[3] - topLeft[1] + h, (byte) 5);
				}
			}
		}
		
		for (int[][] corridor : corridors) {
			for (int[] segment : corridor) {
				for (int w = 0; w < segment[0]; w++) {
					for (int h = 0; h < segment[1]; h++) {
						mapCanvas.setPixel(segment[2] - topLeft[0] + w, segment[3] - topLeft[1] + h, (byte) 5);
					}
				}
			}
		}
		
		Location loc = p.getLocation();
		MapCursorCollection cursors = new MapCursorCollection();
		for (Player player : Bukkit.getOnlinePlayers()) {
			Location ploc = player.getLocation();
			if (loc.distance(ploc) <= 30) {
				int x = (ploc.getBlockX() - topLeft[0]) * 2 - 128;
				int y = (ploc.getBlockZ() - topLeft[1]) * 2 - 128;
				byte direction = (byte) (((ploc.getYaw() + 11.25 + 360) % 360)/22.5);
				cursors.addCursor(x, y, direction);
//				p.sendMessage("" + (byte) (((ploc.getYaw() -11.25) % 360)/22.5) + ", " + ((ploc.getYaw() + 11.25 + 360) % 360));
			}
		}
		mapCanvas.setCursors(cursors);
	}

}
