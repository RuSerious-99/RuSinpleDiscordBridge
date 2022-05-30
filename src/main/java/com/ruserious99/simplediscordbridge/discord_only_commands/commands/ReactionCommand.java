package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ReactionCommand implements ICommand {

    private final DatabaseHandler databaseHandler = new DatabaseHandler();

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {

        System.out.println(textChannel.getId());

        message.delete().queue();
        if (RolesHelp.userHasRole(member, "admin")) {
            if (args.length == 5) {
                String channelId = message.getMentionedChannels().get(0).getId();
                String messageId = args[2];
                String emote = args[3];
                String role = message.getMentionedRoles().get(0).getId();

                TextChannel reactionChannel = guild.getTextChannelById(channelId);
                if (reactionChannel != null) {
                    reactionChannel.addReactionById(messageId, emote).queue();
                }
                databaseHandler.addReactionRole(guild.getId()
                        , channelId
                        , messageId
                        , emote
                        , role);
            }
        }
    }
}
