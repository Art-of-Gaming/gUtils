package cf.electrich.utils;

import cf.electrich.utils.commands.*;
import cf.electrich.utils.events.JoinEvent;
import cf.electrich.utils.utils.CC;
import dev.qjaviy.electricutils.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class ElectricUtils extends JavaPlugin {
    private FileConfiguration language = null;
    private File languageFile = null;
    public String ConfigRoute;
    PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();

    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage(CC.translate("&aSuccessfully enabled ElectricUtils"));
        CommandRegister();
        registerEvents();
        registerConfig();
        registerMessages();
    }

    public void CommandRegister(){
        this.getCommand("help").setExecutor(new HelpCommand(this));
        this.getCommand("utils").setExecutor(new MainCommand(this));
        // Discord & Aliases
        this.getCommand("discord").setExecutor(new DiscordCommand(this));
        this.getCommand("dc").setExecutor(new DiscordCommand(this));
        // TeamSpeak & Aliases
        this.getCommand("teamspeak").setExecutor(new TeamSpeakCommand(this));
        this.getCommand("ts").setExecutor(new TeamSpeakCommand(this));
        // Twitter
        this.getCommand("twitter").setExecutor(new TwitterCommand(this));
        // Store
        this.getCommand("store").setExecutor(new StoreCommand(this));
        this.getCommand("buy").setExecutor(new StoreCommand(this));
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(this), this);
    }
    public void registerConfig(){
        File config = new File(getDataFolder(), "config.yml");
        ConfigRoute = config.getPath();
        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
    public FileConfiguration getMessages(){
        if(language == null){
            reloadMessages();
        }
        return language;
    }

    public void reloadMessages(){
        if(language == null){
            languageFile = new File(getDataFolder(),"language.yml");
        }
        language = YamlConfiguration.loadConfiguration(languageFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(this.getResource("language.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                language.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void saveMessages(){
        try{
            language.save(languageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerMessages(){
        languageFile = new File(this.getDataFolder(),"language.yml");
        if(!languageFile.exists()){
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }



}
