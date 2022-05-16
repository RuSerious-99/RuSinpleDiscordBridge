package com.ruserious99.simplediscordbridge.events;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.discord_only_commands.commands.TicketGuiCommand;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionAddEvent extends ListenerAdapter {

    private int count = 0;

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        if (!event.getMember().getUser().isBot()) {
            if (event.getReactionEmote().getName().equals("\uD83D\uDCDB")) {
                long guildIdLong = event.getGuild().getIdLong();
                String rawMassageId = TicketGuiCommand.ticket_gui.get(guildIdLong);

                if (rawMassageId.equals(event.getMessageId())) {
                    event.getReaction().removeReaction(event.getUser()).queue();
                    event.getGuild().createTextChannel("ticket - " + count).queue();

                    count += 1;
                }
            }
        }
    }
}
