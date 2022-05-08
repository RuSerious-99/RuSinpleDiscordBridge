package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public class ClearCommand implements ICommand {


    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {

        if (args.length == 2) {
            try {
                if (Integer.parseInt(args[1]) > 1 && Integer.parseInt(args[1]) <= 100) {
                    List<Message> messages = textChannel.getHistory().retrievePast(Integer.parseInt(args[1] + 1)).complete();
                    textChannel.deleteMessages(messages).queue();
                    textChannel.sendMessage("Success: you have deleted " + Integer.parseInt(args[1]) + " messages").queue();
                } else {
                    textChannel.sendMessage("usage = !clear <number between 2-100>").queue();
                }
            } catch (InsufficientPermissionException e) {
                textChannel.sendMessage("Sorry, They forgot to give me that permission").queue();
            }
        } else {
            textChannel.sendMessage("usage = !clear <number between 2-100>").queue();
        }
    }
}
