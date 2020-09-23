package plugin.explosiveWand.items;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Wand {
	
	public static ItemStack wand;
	
	public static void init() {
		createWand();
	}
	
	public static void createWand() {
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta stickMeta = stick.getItemMeta();
		stickMeta.setDisplayName("§6Explosive Wand");
		List<String> lore = new ArrayList<>();
		lore.add("§7This special wand allows");
		lore.add("§7you to make things go boom-boom!");
		stickMeta.setLore(lore);
		stickMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		stickMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		stick.setItemMeta(stickMeta);
		
		wand = stick;
	}

}