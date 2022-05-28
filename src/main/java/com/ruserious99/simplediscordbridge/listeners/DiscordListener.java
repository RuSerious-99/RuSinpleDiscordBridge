package com.ruserious99.simplediscordbridge.listeners;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordListener extends ListenerAdapter {

    private final SimpleDiscordBridge simpleDiscordBridge;

    public DiscordListener(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (RolesHelp.hasRole(e.getGuild(), e.getMember(), simpleDiscordBridge.getConfigCommand().getAdmin_Id())) {
            if (e.getChannel().getId().equals(simpleDiscordBridge.getConfigCommand().getGeneralTextChannel())) {
                Bukkit.broadcastMessage(e.getMember().getUser().getAsTag()
                        + ": " + e.getMessage().getContentRaw());
            }
        }
    }
}
