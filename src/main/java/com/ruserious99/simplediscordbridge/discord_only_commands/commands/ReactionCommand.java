package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ReactionCommand implements ICommand {

    private final DatabaseHandler databaseHandler = new DatabaseHandler();
    private SimpleDiscordBridge simpleDiscordBridge;

    public ReactionCommand(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        if (simpleDiscordBridge.getRolesHelp().hasRole(guild, member, simpleDiscordBridge.getConfigCommand().getAdmin_Id())) {
            System.out.println(args.length);
            if (args.length == 5) {
                String channelId = message.getMentionedChannels().get(0).getId();
                String messageId = args[2];
                String emote     = args[3];
                String role      = message.getMentionedRoles().get(0).getId();

                TextChannel reactionChannel = guild.getTextChannelById(channelId);
                if(reactionChannel != null){
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
