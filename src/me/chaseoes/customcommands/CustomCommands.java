package me.chaseoes.customcommands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCommands extends JavaPlugin {
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		getConfig().options().header("CustomCommands v1.0 by chaseoes. http://dev.bukkit.org/server-mods/customcommands/ #");
		getConfig().options().copyHeader(true);
		getServer().getPluginManager().registerEvents(new CommandListener(this), this);
		File configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			List <String> rules = new ArrayList<String>();
			rules.add("&4This is an example CustomCommands rules command!");
			rules.add("&7%name, make sure to follow our rules!");
			rules.add("&c1. No hacking!");
			rules.add("&c2. Have fun!");
			getConfig().addDefault("/rules", rules);
		}
		saveConfig();
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
		if (cmnd.getName().equalsIgnoreCase("customcommands")) {
			if (strings.length == 0) {
				cs.sendMessage("§3[CustomCommands] §6Version §a" + getDescription().getVersion() + " §6by chaseoes.");
				cs.sendMessage("§3[CustomCommands] §dhttp://dev.bukkit.org/server-mods/customcommands/");
			} else if (strings[0].equalsIgnoreCase("reload")) {
				if (strings.length == 1) {
					if (cs.hasPermission("customcommands.reload")) {
						reloadConfig();
						saveConfig();
						cs.sendMessage("§3[CustomCommands] §aSuccessfully reloaded the configuration.");
					} else {
						cs.sendMessage("§3[CustomCommands] §cYou do not have permission to do that.");
					}
				} else {
					cs.sendMessage("§3[CustomCommands] §cIncorrect number of arguments.");
				}
			} else {
				cs.sendMessage("§3[CustomCommands] §cCommand not found.");
			}
		}
		return true;
	}
}
