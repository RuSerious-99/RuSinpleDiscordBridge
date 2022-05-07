package com.ruserious99.simplediscordbridge.commands;

import com.ruserious99.simplediscordbridge.util.Const;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(e.getChannel().getId().equals(Const.GENERAL_TEXT_CHANNEL)) {
            Bukkit.broadcastMessage(e.getMember().getUser().getAsTag()
                    + ": " + e.getMessage().getContentRaw());
        }
    }
}
