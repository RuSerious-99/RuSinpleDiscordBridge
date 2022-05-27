package com.ruserious99.simplediscordbridge.events;

import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReactionAddEvent extends ListenerAdapter {

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        if (event.getChannel().getType() == ChannelType.TEXT) {
            if (!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
                String guildId = event.getGuild().getId();
                String channelId = event.getChannel().getId();
                String messageId = event.getMessageId();
                String emote = event.getReactionEmote().getEmoji();

                String roleId = databaseHandler.findReactionRole(guildId, channelId, messageId, emote);
                event.getGuild().addRoleToMember(event.getMember(), Objects.requireNonNull(event.getGuild().getRoleById(roleId))).queue();
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        if (event.getChannel().getType() == ChannelType.TEXT) {
            if (!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
                String guildId = event.getGuild().getId();
                String channelId = event.getChannel().getId();
                String messageId = event.getMessageId();
                String emote = event.getReactionEmote().getEmoji();

                String roleId = databaseHandler.findReactionRole(guildId, channelId, messageId, emote);
                event.getGuild().removeRoleFromMember(event.getMember(), Objects.requireNonNull(event.getGuild().getRoleById(roleId))).queue();
            }
        }
    }
}
