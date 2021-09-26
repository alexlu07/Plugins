package me.misleaded.tabList.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.misleaded.tabList.tablist.TablistManager;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("setEventNumber")) {
			if (args.length == 0) {
				p.sendMessage("Usage: /setEventNumber <integer>");
				return false;
			}
			
			int i;
			try {
		        i = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        p.sendMessage("Usage: /setEventNumber <integer>");
		        return false;
		    }
			
			TablistManager.setEventNumber(i);
			p.sendMessage("Set Event Number to " + i);
			
		}
		
		return true;
	}

}
