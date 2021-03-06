package me.misleaded.forceWand.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Wand {
	
	public static ItemStack wand;
	
	public static void init() {
		createWand();
	}
	
	public static void createWand() {
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta stickMeta = stick.getItemMeta();
		stickMeta.setDisplayName("§6Force Wand");
		List<String> lore = new ArrayList<>();
		lore.add("§7This special wand allows");
		lore.add("§7you to push entities away!");
		stickMeta.setLore(lore);
		stickMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		stickMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		stick.setItemMeta(stickMeta);
		
		wand = stick;
	}
	
	public static Vector push(Location p, Location e) {
		Vector v = e.subtract(p).toVector();
		v.normalize();
		v.multiply(2);
		v.setY(0.5);
		return v;
		
	}
}
