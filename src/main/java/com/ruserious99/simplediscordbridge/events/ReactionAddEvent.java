package com.ruserious99.simplediscordbridge.events;

import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionAddEvent extends ListenerAdapter {



    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        if(event.getReactionEmote().getId().equals("975510012011085854")){
            System.out.println("crazy bot worked!!!");
        }
    }
}
