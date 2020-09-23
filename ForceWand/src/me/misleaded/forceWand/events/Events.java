package me.misleaded.forceWand.events;

import java.util.Collection;

import org.bukkit.Location;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.misleaded.forceWand.items.Wand;
import me.misleaded.forceWand.util.Rectangle;

public class Events implements Listener{
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		Float yaw = loc.getYaw();
		Action action = e.getAction();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if (item.getItemMeta().getLore().equals(Wand.wand.getItemMeta().getLore())) {
				
//				p.sendMessage("Holding Wand");
				double length = 6.0;
				double width = 2.0;
				double translation = 0.25; 
				double height = 10.0;
				
//				Vector front = new Vector(Math.sin(Math.toRadians(yaw)), 0, Math.cos(Math.toRadians(yaw)));
//				Vector back = new Vector(Math.sin(Math.toRadians(yaw)), 0, Math.cos(Math.toRadians(yaw)));;
//				Vector left = new Vector(Math.sin(Math.toRadians(yaw+90)), 0, Math.cos(Math.toRadians(yaw+90)));
//				Vector right = new Vector(Math.sin(Math.toRadians(yaw-90)), 0, Math.cos(Math.toRadians(yaw-90)));

				Vector front = new Vector(Math.cos(Math.toRadians(yaw+90)), 0, Math.sin(Math.toRadians(yaw+90)));
				Vector back = new Vector(Math.cos(Math.toRadians(yaw+90)), 0, Math.sin(Math.toRadians(yaw+90)));
				Vector left = new Vector(Math.cos(Math.toRadians(yaw)), 0, Math.sin(Math.toRadians(yaw)));
				Vector right = new Vector(Math.cos(Math.toRadians(yaw)), 0, Math.sin(Math.toRadians(yaw)));
				right.multiply(-1);	
				
//				p.sendMessage(front.toString());
				
				front.multiply(length + translation);
				back.multiply(translation);
				
//				p.sendMessage(left.toString());
//				p.sendMessage(right.toString());
				
				left.multiply(width);
				right.multiply(width);
				
//				Vector test = new Vector(0, 0, 0);
//				test.
				
//				p.sendMessage(left.toString());
//				p.sendMessage(right.toString());
				
				
				Vector topLeft = left.clone().add(front);                     
				Vector topRight = right.clone().add(front);
				Vector bottomLeft = left.clone().add(back);
				Vector bottomRight = right.clone().add(back);
				
//				p.sendMessage(front.toString());
//				p.sendMessage(back.toString());
				
				Location p1 = loc.clone().add(topLeft);
				Location p2 = loc.clone().add(topRight);
				Location p3 = loc.clone().add(bottomLeft);				
				Location p4 = loc.clone().add(bottomRight);
								
//				p.sendMessage(left.toString() + "\n" + right.toString());
//				p.sendMessage(p1.toString() + "\n" + p2.toString());	
//				ArmorStand a = (ArmorStand) loc.getWorld().spawn(p1, ArmorStand.class);
//				a.setGravity(false);
//				
//				ArmorStand b = (ArmorStand) loc.getWorld().spawn(p2, ArmorStand.class);
//				b.setGravity(false);
//				
//				ArmorStand c = (ArmorStand) loc.getWorld().spawn(p3, ArmorStand.class);
//				c.setGravity(false);
//				
//				ArmorStand d = (ArmorStand) loc.getWorld().spawn(p4, ArmorStand.class);
//				d.setGravity(false);
				

				Rectangle rect = new Rectangle(p1, p2, p3, p4);
				rect.drawWaves(0.1, p.getLocation().getDirection());
				
//				p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 1, 0.0, 2.0, 0.0, 2);
//				p.sendMessage(Particle.EXPLOSION_LARGE.getDataType().toString());
//				p.sendMessage(rect.toString());
				
				Collection<Entity> entities = p.getWorld().getNearbyEntities(loc, 20, 10, 20);
				for (Entity entity:entities) {
					
					if (rect.inRectangle(entity.getLocation())) {
//						p.sendMessage(entity.toString() + " is in rectangle!");
						double entityY = entity.getLocation().getY();
						if (loc.getY() - height / 2 <= entityY && entityY <= loc.getY() + height / 2) {
							entity.setVelocity(Wand.push(loc, entity.getLocation()));
						}
					}
					
////					p.sendMessage(entity.toString() + entity.getLocation().toVector());
//					Double entityX = entity.getLocation().getX();
//					Double entityY = entity.getLocation().getY();
//					Double entityZ = entity.getLocation().getZ();
//					
//					if (Math.min(p1.getX(), p2.getX()) < entityX && entityX < Math.max(p1.getX(), p2.getX()) &&
//						Math.min(p1.getY(), p2.getY()) < entityY && entityY < Math.max(p1.getY(), p2.getY()) &&
//						Math.min(p1.getZ(), p2.getZ()) < entityZ && entityZ < Math.max(p1.getZ(), p2.getZ())) {
//						
////						p.sendMessage("Entity in range");
//						entity.setVelocity(Wand.push(loc.toVector(), entity.getLocation().toVector()));
//					}
				}
				
//				long timeElapsed = System.nanoTime() - startTime;
//				p.sendMessage("" + timeElapsed);
				
				
			}
		}
	}

}
