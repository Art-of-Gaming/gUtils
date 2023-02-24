package dev.qjaviy.electricutils.commands;

import dev.qjaviy.electricutils.ElectricUtils;
import dev.qjaviy.electricutils.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class MainCommand implements CommandExecutor {
    private ElectricUtils plugin;

    public MainCommand(ElectricUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&cYou cannot execute commands from console!"));
            return false;
        } else {
            Player player = (Player) commandSender;
            if (!player.hasPermission("utils.admin")) {
                FileConfiguration language = plugin.getMessages();
                if (language.contains("NO-PERMISSION-MESSAGE")) {
                    List<String> message = language.getStringList("NO-PERMISSION-MESSAGE");
                    for (int i = 0; i < message.size(); i++) {
                        String text = message.get(i);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
                    }
                }
                return false;
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("version")) {
                    FileConfiguration language = plugin.getMessages();
                    if (language.contains("VERSION-MESSAGE")) {
                        List<String> message = language.getStringList("VERSION-MESSAGE");
                        for (int i = 0; i < message.size(); i++) {
                            String text = message.get(i);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%version%", plugin.version)));
                        }
                    }
                    return true;

                } else if (args[0].equalsIgnoreCase("reload")) {
                    FileConfiguration language = plugin.getMessages();
                    if (language.contains("RELOAD-CONFIG-MESSAGE")) {
                        List<String> message = language.getStringList("RELOAD-CONFIG-MESSAGE");
                        for (int i = 0; i < message.size(); i++) {
                            String text = message.get(i);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%version%", plugin.version)));
                            plugin.reloadConfig();
                            plugin.reloadMessages();
                        }
                    }
                } else {
                    FileConfiguration language = plugin.getMessages();
                    if (language.contains("PLUGIN-HELP-MESSAGE")) {
                        List<String> message = language.getStringList("PLUGIN-HELP-MESSAGE");
                        for (int i = 0; i < message.size(); i++) {
                            String text = message.get(i);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
                        }
                    }
                    return true;
                }
            } else {
                FileConfiguration language = plugin.getMessages();
                if (language.contains("PLUGIN-HELP-MESSAGE")) {
                    List<String> message = language.getStringList("PLUGIN-HELP-MESSAGE");
                    for (int i = 0; i < message.size(); i++) {
                        String text = message.get(i);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
                    }
                }
                return true;
            }
        }
        return false;
    }
}
