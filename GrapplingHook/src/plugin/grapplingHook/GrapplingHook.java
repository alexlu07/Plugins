package plugin.grapplingHook;

import org.bukkit.plugin.java.JavaPlugin;

import plugin.grapplingHook.commands.GrapplingHookCommands;
import plugin.grapplingHook.items.GrapplingHookRod;
import plugin.grapplingHook.events.GrapplingHookEvents;

public class GrapplingHook extends JavaPlugin {
	
	@Override
	public void onEnable() {
		GrapplingHookRod.init();
		getCommand("grapplinghook").setExecutor(new GrapplingHookCommands());
		getServer().getPluginManager().registerEvents(new GrapplingHookEvents(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
