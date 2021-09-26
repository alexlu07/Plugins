package me.misleaded.forceWand.util;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.bukkit.Particle.DustOptions;

public class Particles {
	
	public static void drawLine(Location point1, Location point2, double space) {
        World world = point1.getWorld();
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize();
        double length = 0;
        for (; length < distance; p1.add(vector.clone().multiply(space))) {
        	DustOptions dustOptions = new DustOptions(Color.RED, 1);
            world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 1, dustOptions);
            length += space;
        }
    }
	
	public static void drawWave(Location point1, Location point2, double space, Vector p) {
        World world = point1.getWorld();
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize();
        double length = 0;
        Vector dir = p.multiply(1.4);
        for (; length < distance; p1.add(vector.clone().multiply(space))) {
            world.spawnParticle(Particle.CRIT, p1.getX(), p1.getY()+1, p1.getZ(), 0, dir.getX(), 0, dir.getZ(), 4);
            world.spawnParticle(Particle.CRIT, p1.getX(), p1.getY()+1.2, p1.getZ(), 0, dir.getX(), 0, dir.getZ(), 4);
            world.spawnParticle(Particle.CRIT, p1.getX(), p1.getY()+0.8, p1.getZ(), 0, dir.getX(), 0, dir.getZ(), 4);
            length += space;
        }
    }
}
