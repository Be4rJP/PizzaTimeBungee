package be4rjp.pizzatimebungee;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Config {
    
    private static Configuration configuration;
    
    public static void LoadConfig(){
        
        //Save default config files
        if (!Main.getPlugin().getDataFolder().exists())
            Main.getPlugin().getDataFolder().mkdir();
        File file = new File(Main.getPlugin().getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = Main.getPlugin().getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        //Load config files
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.getPlugin().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static Configuration getConfiguration(){return configuration;}
}
