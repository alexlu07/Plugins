package me.misleaded.doubleJump.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.misleaded.doubleJump.items.DoubleJumpBoots;

public class DoubleJumpBootsCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can you that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("doublejumpboots")) {
			p.sendMessage("§6 You have been given a double jump boots!");
			ItemStack boots = p.getInventory().getBoots();
			if (boots != null) {
				p.getInventory().addItem(boots);
			}
			p.getInventory().setBoots(DoubleJumpBoots.doubleJumpBoots);
		
		}
		
		return true;
	}

}
