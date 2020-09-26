package me.misleaded.amongUs.roomGen;

import java.util.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
 
public class RoomGen {
	public int corridorWidth = 3;
	public int width;
	public int height;
	public int depth;
	public int[] topLeft = new int[2];
	public int[] bottomRight = new int[2];
	public int[] room;
	public RoomGen[] children = new RoomGen[2];
	public int[][] rooms;
	public int[][][] corridors;
	
	public RoomGen(int w, int h, int d, int[] tl) {
		width = w;
		height = h;
		depth = d;
		topLeft = tl;
		bottomRight = new int[]{topLeft[0] + width, topLeft[1] + height};
		/*Minecraft Coord System
		        Neg Z
		          |-3
		          |-2 
		          |-1
		          |0  1  2  3         
Neg Z	----------+---------- Pos X
	   -3 -2 -1  0|    Width(Always Positive)
		         1|  TL-----+
		         2|  |A Room| Height(Always Positive)
		         3|  +------+
		        Pos Z
		*/
		
		// Bottom of the tree
		if (depth == 0) {
			int roomWidth = randRange(width  * (1.0/2), width  * (5.0/6));
			int roomHeight = randRange(height * (1.0/2), height * (5.0/6));
			int[] roomPos = {topLeft[0] + randRange(0, width-roomWidth), topLeft[1] + randRange(0, height-roomHeight)};
		
			room = new int[] {roomWidth, roomHeight, roomPos[0], roomPos[1]};
			
		} else {
			int split, splitVal;
			if (width/height > 1.25) {
				// Wider
				split = 0;
			} else if (height/width > 1.25) {
				// Taller
				split = 1;
			} else {
				// Too similar, use random
				split = randRange(0, 2);
			}
			
			if (split == 0) {
				// Wide, Vertical Cut, height stays same, location along x axis is random
				splitVal = randRange(width  * (1.0/3), width  * (2.0/3)); // width of 1 room
				
				children[0] = new RoomGen(splitVal, height, depth-1, tl);
				children[1] = new RoomGen(width-splitVal, height, depth-1, new int[]{tl[0]+splitVal, tl[1]});
			} else {
				// Tall, Horizontal Cut, width stays same, location along y axis is random
				splitVal = randRange(height * (1.0/3), height * (2.0/3)); // height of 1 room
				
				children[0] = new RoomGen(width, splitVal, depth-1, tl);
				children[1] = new RoomGen(width, height-splitVal, depth-1, new int[]{tl[0], tl[1]+splitVal});
			}
		}
		
	}
	
	public int randRange(double min, double max) {
		Random r = new Random();
		return r.nextInt((int) max - (int) min) + (int) min;
	}
	
	public int[][] getCorridor(int[] r1, int[] r2) {

		int[] p1 = { randRange(r1[2], r1[2] + r1[0] - corridorWidth), randRange(r1[3], r1[3] + r1[1] - corridorWidth) };
		int[] p2 = { randRange(r2[2], r2[2] + r2[0] - corridorWidth), randRange(r2[3], r2[3] + r2[1] - corridorWidth) };

		int hWidth, hHeight, hx, hy;
		int vWidth, vHeight, vx, vy;
		int[] p3;
		
		// Horizontal from p1, then Vertical to p2
//		p3 = (randRange(0, 2) == 0) ? new int[] { p2[0], p1[1] } : new int[] { p1[0], p2[1] };
		if (randRange(0, 2) == 0) {
			// Horizontal first
			p3 = new int[] { p2[0], p1[1] };
		
			hWidth = (p1[0] < p3[0]) ? p3[0] - p1[0] + corridorWidth : p1[0] - p3[0];
			hHeight = corridorWidth;
			hx = (p1[0] < p3[0]) ? p1[0] : p3[0];
			hy = (p1[1] < p3[1]) ? p1[1] : p3[1];
		
			vWidth = corridorWidth;
			vHeight = (p2[1] < p3[1]) ? p3[1] - p2[1] + corridorWidth : p2[1] - p3[1];
			vx = (p2[0] < p3[0]) ? p2[0] : p3[0];
			vy = (p2[1] < p3[1]) ? p2[1] : p3[1];
		} else {
			p3 = new int[] { p1[0], p2[1] };
			
			hWidth = (p2[0] < p3[0]) ? p3[0] - p2[0] + corridorWidth : p2[0] - p3[0];
			hHeight = corridorWidth;
			hx = (p2[0] < p3[0]) ? p2[0] : p3[0];
			hy = (p2[1] < p3[1]) ? p2[1] : p3[1];
		
			vWidth = corridorWidth;
			vHeight = (p1[1] < p3[1]) ? p3[1] - p1[1] + corridorWidth : p1[1] - p3[1];
			vx = (p1[0] < p3[0]) ? p1[0] : p3[0];
			vy = (p1[1] < p3[1]) ? p1[1] : p3[1];
		}
		
		int[][] result = new int[2][4];
		result[0] = new int[] { hWidth, hHeight, hx, hy };
		result[1] = new int[] { vWidth, vHeight, vx, vy };
		return result;

	}
	
	public HashMap<String, Object> genRoomsCorridors() {
		if (depth == 0) {
			
			HashMap<String, Object> rc = new HashMap<>();
			rc.put("rooms", new int[][] {room});
			rc.put("corridors", new int[0][0][0]);
			
			return rc;
		}
		
		
		HashMap<String, Object> a = children[0].genRoomsCorridors();
		HashMap<String, Object> b = children[1].genRoomsCorridors();
		
		// Combine the two list of rooms from its children
		int[][] roomA = (int[][]) a.get("rooms");
		int[][] roomB = (int[][]) b.get("rooms");
		
		int aLength = roomA.length;
		int bLength = roomB.length;
		
		int[][] roomCombined = new int[aLength + bLength][4];
		System.arraycopy(roomA, 0, roomCombined, 0, aLength);
		System.arraycopy(roomB, 0, roomCombined, aLength, bLength);
		
		// Combine the two list of corridors from its children. Also add in a new corridor
		int[][][] corridorA = (int[][][]) a.get("corridors");
		int[][][] corridorB = (int[][][]) b.get("corridors");
		
		int[] room1 = roomA[randRange(0, aLength)];
		int[] room2 = roomB[randRange(0, bLength)];
		
		aLength = corridorA.length;
		bLength = corridorB.length;
//		1 2 3 4 5 6 7 8
//		 1   2   3   4
//		   1       2
//		       1
		int[][] newCorridor = getCorridor(room1, room2);
		
		int[][][] corridorCombined = new int[aLength + bLength + 1][][];
		System.arraycopy(corridorA, 0, corridorCombined, 0, aLength);
		System.arraycopy(corridorB, 0, corridorCombined, aLength, bLength);
		corridorCombined[aLength + bLength] = newCorridor;
		
		// Put the combined rooms and corridors into a HashMap to return it
		HashMap<String, Object> roomsCorridors = new HashMap<String, Object>();
		roomsCorridors.put("rooms", roomCombined);
		roomsCorridors.put("corridors", corridorCombined);
		
		rooms = roomCombined;
		corridors = corridorCombined;
		
		return roomsCorridors;
	}
	
	public void drawMap(World world) {
		Material[] layout = { Material.QUARTZ_BLOCK, 
							  Material.STONE_BRICKS, 
							  Material.SEA_LANTERN, 
							  Material.STONE_BRICKS, 
							  Material.GLASS, 
							  Material.QUARTZ_BLOCK,
							  Material.AIR};
		
		for (int y = 0; y < layout.length; y++) {
			for (int w = -1; w < width; w++) {
				for (int h = -1; h < height; h++) {
					world.getBlockAt(new Location(world, topLeft[0] + w, 199 + y, topLeft[1] + h)).setType(layout[y]);
				}
			}				
		}
	
		for (int[] room : rooms) {
			for (int y = 0; y < 4; y++) {
				for (int w = 0; w < room[0]; w++) {
					for (int h = 0; h < room[1]; h++) {
						world.getBlockAt(new Location(world, room[2] + w, 200 + y, room[3] + h)).setType(Material.AIR);
					}
				}
			}
			for (int w = 0; w < room[0]; w++) {
				for (int h = 0; h < room[1]; h++) {
					world.getBlockAt(new Location(world, room[2] + w, 200 + 5, room[3] + h)).setType(Material.ORANGE_CARPET);
				}
			}
		}
		
		for (int[][] corridor: corridors) {
			for (int[] segment : corridor) {
				for (int y = 0; y < 4; y++) {
					for (int w = 0; w < segment[0]; w++) {
						for (int h = 0; h < segment[1]; h++) {
							world.getBlockAt(new Location(world, segment[2] + w, 200 + y, segment[3] + h)).setType(Material.AIR);
						}
					}
				}
				for (int w = 0; w < segment[0]; w++) {
					for (int h = 0; h < segment[1]; h++) {
						world.getBlockAt(new Location(world, segment[2] + w, 200 + 5, segment[3] + h)).setType(Material.ORANGE_CARPET);
					}
				}
			}
		}
	}

}
