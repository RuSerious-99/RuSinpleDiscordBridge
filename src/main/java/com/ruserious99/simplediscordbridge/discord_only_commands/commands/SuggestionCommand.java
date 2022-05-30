package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class SuggestionCommand implements ICommand {
    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        if (args.length > 1) {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            TextChannel suggestionChannel = guild.getTextChannelById(databaseHandler.getSuggestionsChannelId());
            if (suggestionChannel != null) {
                String suggestion = message.getContentRaw().replace("!suggestion", "");
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.green);
                embedBuilder.setTitle("Suggestion: Added by " + member.getUser().getName());
                embedBuilder.setFooter("Suggestion System");
                embedBuilder.setThumbnail(member.getUser().getAvatarUrl());
                embedBuilder.setDescription(suggestion);

                suggestionChannel.sendMessageEmbeds(embedBuilder.build()).queue(message1 -> {
                    message1.addReaction("U+2705").queue();
                    message1.addReaction("U+274C").queue();

                    member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Success, added a suggestion").queue());
                });
            }else {
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Awww snap, Cant find the Chanell").queue());
            }
        } else {
            member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("usage = !suggestion <suggestion>").queue());
        }
    }
}