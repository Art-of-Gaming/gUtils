package dev.qjaviy.electricutils.events;

import dev.qjaviy.electricutils.ElectricUtils;
import dev.qjaviy.electricutils.utils.CC;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinEvent implements Listener {
    private ElectricUtils plugin;

    public JoinEvent(ElectricUtils plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        FileConfiguration settings = plugin.getConfig();

        String path = "WELCOME-MESSAGE-ENABLED";
        if (settings.getString(path).equals("true")){
            FileConfiguration language = plugin.getMessages();
            List<String> message = language.getStringList("WELCOME-MESSAGE");
            for(int i=0;i<message.size();i++){
                String text = message.get(i);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%player%", player.getName())));

            }
        }
        return;
    }
}
