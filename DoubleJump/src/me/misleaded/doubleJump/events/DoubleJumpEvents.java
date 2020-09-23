package me.misleaded.doubleJump.events;



import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import me.misleaded.doubleJump.items.DoubleJumpBoots;

public class DoubleJumpEvents implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		
	if (p.getInventory().getBoots() == null) {
		p.setAllowFlight(false);
	} else if (p.getInventory().getBoots().getItemMeta().getLore().equals(DoubleJumpBoots.doubleJumpBoots.getItemMeta().getLore())) {
		LivingEntity le = p;
		if (le.isOnGround()) {
				p.setAllowFlight(true);
			} else {
				p.setAllowFlight(true);
			}
		}
	}
	
	@EventHandler
	public void onDoubleJump(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		e.setCancelled(true);
		p.setFlying(false);
		p.setAllowFlight(false);
		Location loc = p.getLocation();
		Vector v = loc.getDirection().multiply(1).setY(1);
		p.setVelocity(v);
		loc.getWorld().spawnParticle(Particle.CLOUD, loc, 300, 0.75, 0.1, 0.75, 0.01);
	}
}
