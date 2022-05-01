package com.ruserious99.simplediscordbridge;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(e.getChannel().getId().equals("969822792520454207")) {
            Bukkit.broadcastMessage(e.getMember().getUser().getAsTag()
                    + ": " + e.getMessage().getContentRaw());
        }
    }
}
