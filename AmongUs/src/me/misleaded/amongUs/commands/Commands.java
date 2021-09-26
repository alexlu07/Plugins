package me.misleaded.amongUs.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import me.misleaded.amongUs.genGame.Renderer;
import me.misleaded.amongUs.genGame.RoomGen;
import me.misleaded.amongUs.playGame.Queues;

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
			map.drawMap(p.getWorld());
			
			int[] tl = map.getTL();
			int[][] rooms = map.getRooms();
			int[][][] corridors = map.getCorridors();
			
			p.teleport(new Location(p.getWorld(), rooms[0][2]+rooms[0][0]/2, 201, rooms[0][3]+rooms[0][1]/2));
			
			MapView mapView = Bukkit.createMap(p.getWorld());
			mapView.getRenderers().clear();
			mapView.addRenderer(new Renderer(tl, rooms, corridors));
			
			ItemStack item = new ItemStack(Material.FILLED_MAP);
			MapMeta mapMeta = (MapMeta) item.getItemMeta();
			mapMeta.setMapView(mapView);
			item.setItemMeta(mapMeta);
			
			p.getInventory().addItem(item);
					
		} else if (command.getName().equalsIgnoreCase("join")) {
			if (args.length != 1) {
				p.sendMessage("§4Arguments: queue");
				return true;
			}
			
			if (Queues.queued(p) && !Queues.inQueue(p, args[0])) {
				p.sendMessage("You are already in a queue");
				p.sendMessage("To join a new queue leave your current queue first(/leave)");
				return true;
			}
			
			if (Queues.queueExists(args[0])) {
				if (Queues.joinQueue(p, args[0])) {
					for (Player player : Queues.getQueuedPlayers(args[0])) {
						if (player.equals(p)) {
							player.sendMessage("You are now queued for " + args[0]);
						} else {
							player.sendMessage(p.getName() + " has joined the queue");
						}
						player.sendMessage("Players: " + Queues.getNumQueued(args[0]));
						player.sendMessage("Players needed to start: " + (10 - Queues.getNumQueued(args[0])));
					}
				} else {
					p.sendMessage("You are already in that queue");
				}
			} else {
				p.sendMessage("Queues: " + Queues.getQueues());
			}
		
		} else if (command.getName().equalsIgnoreCase("leave")) {
			
			String queuename = Queues.leaveQueue(p);
			if (queuename != null) {
				p.sendMessage("You have left the queue " + queuename);
			} else {
				p.sendMessage("You are not in a queue");
			}
			
		} else if (command.getName().equalsIgnoreCase("create")) {
			if (args.length != 1) {
				p.sendMessage("§4Arguments: new queue");
				return true;
			}
			
			if (Queues.queued(p)) {
				p.sendMessage("You are already in a queue");
				p.sendMessage("To create a new queue leave your current queue first(/leave)");
				return true;
			}
			
			if (Queues.createQueue(args[0], p)) {
				p.sendMessage("Queue created: " + args[0]);
				p.sendMessage("You have been automatically added to your new queue");
			} else {
				p.sendMessage("Queue already exists");
				p.sendMessage("Existing Queues: " + Queues.getQueues());
			}
		} else if (command.getName().equalsIgnoreCase("forcestart")) {
			if (args.length != 1) {
				p.sendMessage("§4Arguments: queue");
				return true;
			}
			
			if (Queues.forceStart(args[0])) {
				p.sendMessage("Queue has been force started");
			} else {
				p.sendMessage("Queue does not exist");
			}
		}
		
		return true;
	}

}
