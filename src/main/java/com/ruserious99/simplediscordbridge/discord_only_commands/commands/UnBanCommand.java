package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Objects;

public class UnBanCommand implements ICommand {
    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        if (RolesHelp.userHasRole(member, "admin")) {
            if (args.length == 2) {
                guild.unban(args[1]).queue();
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Success: You UnBanned " + Objects.requireNonNull(guild.getMemberById(args[1])).getUser().getName()).queue());
            } else {
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("usage = !unban <user-id>").queue());
            }
        } else {
            member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Sorry, They forgot to give you that role").queue());
        }
    }
}
