package plugin.grapplingHook.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GrapplingHookRod {
	
	public static ItemStack grapplingHookRod;
	
	public static void init() {
		createGrapplingHookRod();
	}
	
	public static void createGrapplingHookRod() {
		ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta rodmeta = rod.getItemMeta();
		rodmeta.setDisplayName("§6Grappling Hook");
		List<String> lore = new ArrayList<>();
		lore.add("§7This special fishing rod allows");
		lore.add("§7you to grapple on to things!");
		rodmeta.setLore(lore);
		rod.setItemMeta(rodmeta);
		grapplingHookRod = rod;
	}
}
