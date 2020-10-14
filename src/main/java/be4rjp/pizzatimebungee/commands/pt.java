package be4rjp.pizzatimebungee.commands;

import be4rjp.pizzatimebungee.Config;
import be4rjp.pizzatimebungee.Main;
import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBungee;
import com.github.ucchyocean.lc3.channel.Channel;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class pt extends Command {
    
    public pt(){
        super("pt");
    }
    
    public void execute(CommandSender sender, String[] args) {
    
        //------------------------Check sender type-----------------------------
        CommanderType type = CommanderType.CONSOLE;
        
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            type = CommanderType.MEMBER;
            if(p.hasPermission("pt.admin"))
                type = CommanderType.ADMIN;
        }
        //----------------------------------------------------------------------
        
        
        
        
        //---------------------------/pt commands-------------------------------
        if(args == null) return;
        if(args.length != 1) return;
        if(type == CommanderType.MEMBER) {
            TextComponent message = new TextComponent("You don't have permission!");
            message.setColor(ChatColor.RED);
            sender.sendMessage(message);
            return;
        }
        //-------------------------/pt reload------------------------
        if(args[0].equals("reload")){
            Main.getPlugin().getProxy().getScheduler().runAsync(Main.getPlugin(),
                new Runnable() {
                    @Override
                    public void run() {
                        Config.LoadConfig();
                        //Create channel
                        LunaChatAPI api = LunaChatBungee.getInstance().getLunaChatAPI();
                        for (String server : Config.getConfiguration().getSection("servers").getKeys()) {
                            String channelName = Config.getConfiguration().getString("servers." + server + ".channel");
                            if (!api.isExistChannel(channelName))
                                api.createChannel(channelName);
                            Channel channel = api.getChannel(channelName);
                            channel.setFormat(LunaChatBungee.getInstance().getConfig().getDefaultFormat());
                        }
    
                        TextComponent message = new TextComponent("The configuration was successfully reloaded!");
                        message.setColor(ChatColor.GREEN);
                        sender.sendMessage(message);
                    }
                }
            );
        }
        //-----------------------------------------------------------
        
        //----------------------------------------------------------------------
    }
}
