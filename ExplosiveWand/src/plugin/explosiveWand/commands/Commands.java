package plugin.explosiveWand.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.explosiveWand.items.Wand;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can you that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("explosivewand")) {
			p.sendMessage("§6 You have been given an Explosive Wand!");
			p.getInventory().addItem(Wand.wand);
		}
		
		return true;
	}

}