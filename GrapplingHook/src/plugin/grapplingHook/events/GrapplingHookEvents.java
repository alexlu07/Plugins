package plugin.grapplingHook.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import plugin.grapplingHook.items.GrapplingHookRod;

public class GrapplingHookEvents implements Listener {
	
	@EventHandler
	public void onRod(PlayerFishEvent e) {
		Player p = (Player) e.getPlayer();
		if (p.getInventory().getItemInMainHand().getItemMeta().getLore().equals(GrapplingHookRod.grapplingHookRod.getItemMeta().getLore())) {
			switch (e.getState()) {
				case FISHING:
					break;
				default:
					Location loc = p.getLocation();
					double distance = e.getHook().getLocation().distance(p.getLocation());
					Vector dir = loc.toVector().subtract(e.getHook().getLocation().toVector()).multiply(-1);
					Vector v = dir.multiply(0.5*distance).setY(Math.abs(e.getHook().getLocation().getX()-p.getLocation().getX() + e.getHook().getLocation().getZ()-p.getLocation().getZ())*0.15+0.65);
					p.setVelocity(v);
			}
		}
	}
	
}
