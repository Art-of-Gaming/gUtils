package cf.electrich.utils.commands;

import cf.electrich.utils.ElectricUtils;
import cf.electrich.utils.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand implements CommandExecutor {

    private ElectricUtils plugin;

    public HelpCommand(ElectricUtils plugin){
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
            if (language.contains("HELP-MESSAGE")) {
                List<String> message = language.getStringList("HELP-MESSAGE");
                for (int i = 0; i < message.size(); i++) {
                    String text = message.get(i);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
                }
            }
            return true;
        }
    }
}