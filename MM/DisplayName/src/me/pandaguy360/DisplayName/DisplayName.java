package me.pandaguy360.DisplayName;
import org.bukkit.plugin.java.JavaPlugin;
import me.pandaguy360.DisplayName.Events.*;

public class DisplayName extends JavaPlugin {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	@Override
	public void onDisable() {
		
	}
}