package me.misleaded.forceWand.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Rectangle {
	private Location topLeft;
	private Location topRight;
	private Location bottomLeft;
	private Location bottomRight;
	
	private Equation left;
	private Equation right;
	private Equation top;
	private Equation bottom;
	
	public Rectangle(Location tl, Location tr, Location bl, Location br) {
		topLeft = tl;
		topRight = tr;
		bottomLeft = bl;
		bottomRight = br;
		
		left = new Equation(topLeft.getX(), topLeft.getZ(), bottomLeft.getX(), bottomLeft.getZ());
		right = new Equation(topRight.getX(), topRight.getZ(), bottomRight.getX(), bottomRight.getZ());
		top = new Equation(topLeft.getX(), topLeft.getZ(), topRight.getX(), topRight.getZ());
		bottom = new Equation(bottomLeft.getX(), bottomLeft.getZ(), bottomRight.getX(), bottomRight.getZ());
		
		setTypes();
	}
	
	private void setTypes() {
		left.setType((topLeft.getZ() > topRight.getZ()) ? 3 : 4);
		right.setType((topRight.getZ() > topLeft.getZ()) ? 3 : 4);
		top.setType((topLeft.getZ() > bottomLeft.getZ()) ? 3 : 4);
		bottom.setType((bottomLeft.getZ() > topLeft.getZ()) ? 3 : 4);
		
	}
	
	public String toString() {
		return "§6LEFT:\n" + "§f" + left.toString() + 
			   "§6RIGHT:\n" + "§f" + right.toString() + 
			   "§6TOP:\n" + "§f" + top.toString() + 
			   "§6BOTTOM:\n" + "§f" + bottom.toString();
	}
	
	public void drawLines() {
		Particles.drawLine(topLeft, bottomLeft, 0.2);
		Particles.drawLine(topRight, bottomRight,0.2);
		Particles.drawLine(topLeft, topRight, 0.2);
		Particles.drawLine(bottomLeft, bottomRight, 0.2);
	}

	public void drawWaves(double space, Vector direction) {
		Particles.drawWave(bottomLeft, bottomRight, space, direction);
	}
	
	public boolean inRectangle(Location l) {
		double x = l.getX();
		double z = l.getZ();
		
		if (left.solve(x, z) == false) {
			return false;

		}
		
		if (right.solve(x, z) == false) {
			return false;
			
		}
		
		if (top.solve(x, z) == false) {
			return false;
			
		}
		
		if (bottom.solve(x, z) == false) {
			return false;
			
		}
		
		
		return true;
		
	}
}
