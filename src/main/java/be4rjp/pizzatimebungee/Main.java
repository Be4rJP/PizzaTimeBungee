package be4rjp.pizzatimebungee;

import be4rjp.pizzatimebungee.commands.pt;
import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBungee;
import com.github.ucchyocean.lc3.channel.Channel;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.protocol.packet.Commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Main extends Plugin {
    
    private static Main plugin;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting up PizzaTime...");

        plugin = this;

        PluginManager pm = getProxy().getPluginManager();
        pm.registerListener(this, new EventListener());


        getLogger().info("Loading config files...");

        Config.LoadConfig();

        //Create channel
        LunaChatAPI api = LunaChatBungee.getInstance().getLunaChatAPI();
        for(String server : Config.getConfiguration().getSection("servers").getKeys()) {
            String channelName = Config.getConfiguration().getString("servers." + server + ".channel");
            if(!api.isExistChannel(channelName))
                api.createChannel(channelName);
            Channel channel = api.getChannel(channelName);
            channel.setFormat(LunaChatBungee.getInstance().getConfig().getDefaultFormat());
        }

        if(!Config.getConfiguration().getString("default-channel").equalsIgnoreCase("")) {
            //Create default channel
            String channelName = Config.getConfiguration().getString("default-channel");
            if (!api.isExistChannel(channelName))
                api.createChannel(channelName);
            Channel channel = api.getChannel(channelName);
            channel.setFormat(LunaChatBungee.getInstance().getConfig().getDefaultFormat());
        }
        
        //setup command
        getProxy().getPluginManager().registerCommand(this, new pt());
        
        getLogger().info("PizzaTime is Enabled!");
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public static Main getPlugin(){return plugin;}
}
