package me.chaseoes.customcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandListener implements Listener {
	private final JavaPlugin plugin;
	public CommandListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		for (String command : plugin.getConfig().getKeys(false)) {
			if (event.getMessage().equalsIgnoreCase(command)) {
				event.setCancelled(true);
				if (player.hasPermission("customcommands.command." + command)) {
					for (String msg : plugin.getConfig().getStringList(command)) {
						player.sendMessage(formatVariables(msg, player));
					}
				} else {
					player.sendMessage("§3[CustomCommands] §cYou do not have permission to do that.");
				}
			}
		}
	}
	
	public String formatVariables(String string, Player player) {
		return ChatColor.translateAlternateColorCodes("&".charAt(0), string)
				.replace("%name", player.getName())
				.replace("%displayname", player.getDisplayName());
	}
	
}
