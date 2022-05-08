package com.ruserious99.simplediscordbridge.discord_only_commands.type;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface ICommand {

    void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message);
}
