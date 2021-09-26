package me.misleaded.aim.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.misleaded.aim.Aim;
import me.misleaded.aim.runnables.TrackingRunnable;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can you that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("start")) {
			p.sendMessage("§6 Started!");
			
		
		}
		new TrackingRunnable(p).runTaskTimer(Aim.plugin, 1, 1);

		return true;
	}

}
	