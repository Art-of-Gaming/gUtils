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

public class StoreCommand implements CommandExecutor {
    private ElectricUtils plugin;

    public StoreCommand(ElectricUtils plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&cYou cannot execute commands from console!"));
            return false;
        }else{
            Player player = (Player) commandSender;
            FileConfiguration language = plugin.getMessages();
            FileConfiguration settings = plugin.getConfig();

            String path = "STORE-COMMAND-ENABLED";

            if (language.contains("SOCIALS.STORE-MESSAGE")) {
                if (settings.getString(path).equals("true")) {
                    List<String> message = language.getStringList("SOCIALS.STORE-MESSAGE");
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
