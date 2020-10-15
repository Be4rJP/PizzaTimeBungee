package be4rjp.pizzatimebungee;

import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBungee;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.member.ChannelMember;
import com.github.ucchyocean.lc3.member.ChannelMemberBungee;
import com.github.ucchyocean.lc3.member.ChannelMemberProxiedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Collection;

public class EventListener implements Listener {
    
    @EventHandler
    public void onConnectServer(ServerConnectedEvent event){
        
        String playerName = event.getPlayer().getName();
        String serverName = event.getServer().getInfo().getName();
        ProxiedPlayer player = event.getPlayer();

        Main.getPlugin().getProxy().getScheduler().runAsync(Main.getPlugin(),
            new Runnable() {
                @Override
                public void run() {
                    LunaChatAPI api = LunaChatBungee.getInstance().getLunaChatAPI();
                    ChannelMemberBungee member = ChannelMemberBungee.getChannelMemberBungee(player);

                    //一度すべてのチャンネルから退出させる
                    Collection<Channel> channels = api.getChannels();
                    for (Channel channel : channels)
                        if (channel.getMembers().contains(member) && !channel.isGlobalChannel() && !channel.isForceJoinChannel())
                            channel.removeMember(member);
                    if (api.getDefaultChannel(playerName) != null)
                        api.removeDefaultChannel(playerName);

                    //設定を参照してチャンネルに参加させる
                    for (String server : Config.getConfiguration().getSection("servers").getKeys()) {
                        String channelName = Config.getConfiguration().getString("servers." + server + ".channel");
                        if (server.equals(serverName)) {
                            Channel channel = api.getChannel(channelName);
                            if (channel.getMembers().contains(member)) {
                                // デフォルトの発言先に設定する
                                if (api.getDefaultChannel(player.getName()) == null ||
                                        !api.getDefaultChannel(player.getName()).getName().equals(channelName)) {
                                    api.setDefaultChannel(player.getName(), channelName);
                                }

                            } else {
                                // チャンネルに参加する
                                if (!channel.getName().equals(LunaChatBungee.getInstance().getConfig().getGlobalChannel())) {
                                    channel.addMember(member);
                                }
                                // デフォルトの発言先に設定する
                                if (api.getDefaultChannel(player.getName()) == null ||
                                        !api.getDefaultChannel(player.getName()).getName().equals(channelName)) {
                                    api.setDefaultChannel(player.getName(), channelName);
                                }
                            }
                            return;
                        }
                    }

                    //設定になければdefault channelに参加させる
                    String channelName = Config.getConfiguration().getString("default-channel").equalsIgnoreCase("")
                            ? LunaChatBungee.getInstance().getConfig().getGlobalChannel()
                            : Config.getConfiguration().getString("default-channel");
                    Channel channel = api.getChannel(channelName);
                    if(channel != null) {
                        if (channel.getMembers().contains(member)) {
                            // デフォルトの発言先に設定する
                            if (api.getDefaultChannel(player.getName()) == null ||
                                    !api.getDefaultChannel(player.getName()).getName().equals(channelName)) {
                                api.setDefaultChannel(player.getName(), channelName);
                            }

                        } else {
                            // チャンネルに参加する
                            if (!channel.getName().equals(LunaChatBungee.getInstance().getConfig().getGlobalChannel())) {
                                channel.addMember(member);
                            }
                            // デフォルトの発言先に設定する
                            if (api.getDefaultChannel(player.getName()) == null ||
                                    !api.getDefaultChannel(player.getName()).getName().equals(channelName)) {
                                api.setDefaultChannel(player.getName(), channelName);
                            }
                        }
                    }

                }
            }
        );
    }
}
