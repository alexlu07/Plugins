package me.misleaded.aim.runnables;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class TrackingRunnable extends BukkitRunnable {
	private Player p;
	
	public TrackingRunnable(Player player) {
		p = player;
	}

	@Override
	public void run() {
		boolean hit = false;
		for (Entity mob : p.getNearbyEntities(10, 10, 10)) {
			if (mob.getType() != EntityType.RABBIT) {
				continue;
			}
		    Location eye = p.getEyeLocation();
		    Vector toEntity = ((LivingEntity) mob).getEyeLocation().toVector().subtract(eye.toVector());
		    double dot = toEntity.normalize().dot(eye.getDirection());
		   
		    if (dot > 0.99D) {
		    	hit = true;
		    	p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 6);
		    	break;
		    }
		}
		if (hit == false) {
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, (float) 0.5, 3);
		}
	}
}
