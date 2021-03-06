package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;


public class JustHello implements ICommand {

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        String m = message.getContentRaw();
        if (m.equalsIgnoreCase("hello")) {
            textChannel.sendMessage("Hello " + member.getUser().getName()).queue();
        }
    }
}
