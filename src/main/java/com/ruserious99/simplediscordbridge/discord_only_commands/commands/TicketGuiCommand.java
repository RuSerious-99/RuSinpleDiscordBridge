package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import com.ruserious99.simplediscordbridge.util.Emotes;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.util.HashMap;

public class TicketGuiCommand implements ICommand {

    //todo make with database
    public static HashMap<Long, String> ticket_gui = new HashMap<>();


    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {

        if(args.length == 2){
            TextChannel textChannel1 = message.getMentionedChannels().get(0);
            if(textChannel1 != null){
                EmbedBuilder embedBuilder = new EmbedBuilder();

                embedBuilder.setColor(Color.gray);
                embedBuilder.setDescription("Server Ticket System");
                embedBuilder.setDescription("React to this panel to receive support\n by creating a channel, please dont\n abuse this feature.");

                textChannel1.sendMessageEmbeds(embedBuilder.build()).queue(message1 ->{
                    message1.addReaction("U+1F4DB").queue();
                    long guildId = guild.getIdLong();
                    String messageId = message1.getId();

                    if(!ticket_gui.containsKey(guildId)){
                        ticket_gui.put(guildId, messageId);
                    }
                });
            }
        }
    }
}
