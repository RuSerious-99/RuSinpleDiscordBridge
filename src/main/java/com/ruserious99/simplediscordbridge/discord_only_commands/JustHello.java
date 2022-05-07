package com.ruserious99.simplediscordbridge.discord_only_commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JustHello extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        String message = e.getMessage().getContentRaw();
        if(message.equalsIgnoreCase("hello")) {
            if (!e.getAuthor().isBot()) {
                e.getChannel().sendMessage("Hello " + e.getAuthor().getName()).queue();
            }
        }
    }
}
