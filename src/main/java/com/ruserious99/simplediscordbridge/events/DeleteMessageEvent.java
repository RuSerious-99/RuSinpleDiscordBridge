package com.ruserious99.simplediscordbridge.events;

import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteMessageEvent extends ListenerAdapter {

    DatabaseHandler databaseHandler = new DatabaseHandler();
    List<String> badwords = new ArrayList<>();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (badwords.isEmpty() || badwords.size() != databaseHandler.getBadWordCount()) {
            badwords.clear();
            badwords = databaseHandler.getBadWords();
        }
        if (!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
            String message = event.getMessage().getContentRaw();
            if (!message.contains("!badword")) {
                for (String s : badwords) {
                    if (message.contains(s)) {
                        event.getMessage().delete().queue();
                        event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Word not permitted " + s + " message was deleted").queue());
                    }
                }
            }
        }
    }
}