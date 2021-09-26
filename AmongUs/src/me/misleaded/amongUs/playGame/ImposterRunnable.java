package me.misleaded.amongUs.playGame;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ImposterRunnable extends BukkitRunnable {
	private Player imposter;
	
	public ImposterRunnable(Player impost) {
		imposter = impost;
	}

	@Override
	public void run() {
		for (Player p : imposter.getServer().getOnlinePlayers()) {
			if (p.getLocation().distance(imposter.getLocation()) < 3 && !p.equals(imposter)) {
				imposter.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(p.getName()));
			}
		}
	}
}
