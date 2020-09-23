 package me.misleaded.doubleJump.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DoubleJumpBoots {
	public static ItemStack doubleJumpBoots;
	
	public static void init() {
		createDoubleJumpBoots();
	}
	
	public static void createDoubleJumpBoots() {
		ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
		ItemMeta bootsmeta = boots.getItemMeta();
		bootsmeta.setDisplayName("§6Double Jump Boots");
		List<String> lore = new ArrayList<>();
		lore.add("§7This special boots allows");
		lore.add("§7you to jump twice in the air!");
		bootsmeta.setLore(lore);
		boots.setItemMeta(bootsmeta);
		doubleJumpBoots = boots;
	}	
}
