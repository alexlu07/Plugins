package me.misleaded.amongUs.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.misleaded.amongUs.roomGen.RoomGen;


public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can you that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("genMap")) {
			if (args.length != 5) {
				p.sendMessage("§4Arguments: width, height, depth, x, y");
				return true;
			} 
			
			int width = Integer.parseInt(args[0]);
			int height = Integer.parseInt(args[1]);
			int depth = Integer.parseInt(args[2]);
			int x = Integer.parseInt(args[3]);
			int z = Integer.parseInt(args[4]);

			RoomGen map = new RoomGen(width, height, depth, new int[] { x, z });
			map.genRoomsCorridors();
			
			map.drawMap(p.getWorld());
			
			int[][] rooms = map.rooms;
			p.teleport(new Location(p.getWorld(), rooms[0][2]+rooms[0][0]/2, 201, rooms[0][3]+rooms[0][1]/2));
			p.getInventory().addItem(new ItemStack(Material.MAP));
		}
		
		return true;
	}

}
