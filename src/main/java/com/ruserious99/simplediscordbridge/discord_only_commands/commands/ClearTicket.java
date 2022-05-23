package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import com.ruserious99.simplediscordbridge.listeners.ButtonClick;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearTicket implements ICommand {


    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        if (args.length == 1) {
            if (ButtonClick.hasOpenTicket.containsKey(textChannel.getId())) {
                if (member.hasPermission(Permission.ADMINISTRATOR)) {
                    member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Successfully deleting channel with id " + textChannel.getId()).queue());
                    ButtonClick.hasOpenTicket.remove(textChannel.getId());
                    textChannel.delete().queue();
                }
            }
        }
    }
}
