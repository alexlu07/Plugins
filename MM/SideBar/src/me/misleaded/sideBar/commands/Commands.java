package me.misleaded.sideBar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.misleaded.sideBar.sidebar.SidebarManager;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("activateSidebar")) {			
			SidebarManager.activate(p);
			p.sendMessage("" + ChatColor.GREEN + "Scoreboard Displayed on Tablist!");
			
			
		}
		
		else if (command.getName().equalsIgnoreCase("deactivateSidebar")) {			
			SidebarManager.deactivate(p);
			p.sendMessage("" + ChatColor.RED + "Scoreboard No Longer Displayed on Tablist!");
			
		}
		return true;
	}


}

