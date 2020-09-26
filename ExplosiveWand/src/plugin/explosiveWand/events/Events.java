package plugin.explosiveWand.events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import plugin.explosiveWand.items.Wand;

public class Events implements Listener {
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) throws NullPointerException {
				
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		
		Action action = e.getAction();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (item.equals(Wand.wand)) {

				Location projLocation = loc.clone().add(0.0, 1.1, 0.0);
				projLocation.add(loc.getDirection());
				
				
				Fireball fireball = p.getWorld().spawn(projLocation, Fireball.class);
				fireball.addScoreboardTag("explosiveProjectile");
				fireball.setYield((float) 2.5);
				
//				FallingBlock magma = loc.getWorld().spawnFallingBlock(loc, Material.MAGMA_BLOCK.createBlockData());
//				Entity z = loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
				
				Vector v = loc.getDirection();
				v.multiply(1.5);

				
				
				fireball.setVelocity(v);
//				fireball.addPassenger(z);

			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Projectile proj = e.getEntity();
//		Location loc = proj.getLocation();
		
		if (proj.getScoreboardTags().contains("explosiveProjectile")) {

//			proj.getPassengers().get(0).remove();
			
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		Entity entity = e.getEntity();
		Location loc = entity.getLocation();
		if (entity.getScoreboardTags().contains("explosiveProjectile")) {
			List<Block> blocks = e.blockList();
			for (Block block: blocks) {
				FallingBlock fallingBlock = loc.getWorld().spawnFallingBlock(block.getLocation(), block.getBlockData());
				Vector normalized = block.getLocation().subtract(loc).toVector().normalize().setY(1);
				Vector velocity = normalized.multiply(1 - (loc.distanceSquared(block.getLocation()) / 16));
				fallingBlock.setVelocity(velocity);
				fallingBlock.setDropItem(false);
				block.setType(Material.AIR);
			}
		}
		
	}

}
