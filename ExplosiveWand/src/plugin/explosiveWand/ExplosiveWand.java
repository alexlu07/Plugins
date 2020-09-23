package plugin.explosiveWand;

import org.bukkit.plugin.java.JavaPlugin;

import plugin.explosiveWand.commands.Commands;
import plugin.explosiveWand.events.Events;
import plugin.explosiveWand.items.Wand;

public class ExplosiveWand extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Wand.init();
		getCommand("explosivewand").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
