package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import com.ruserious99.simplediscordbridge.util.Const;
import com.ruserious99.simplediscordbridge.util.MembersHelp;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;


public class KickCommand implements ICommand {


    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        if (RolesHelp.hasRole(guild, member, Const.ADMIN_ID)) {
            if (args.length == 3) {
                Member target = MembersHelp.getMemberasMember(args, guild);
                if (target != null) {
                    target.kick(args[2]).queue();
                    textChannel.sendMessage("Success: You Kicked " + args[1] + " for " + args[2]).queue();
                }
            } else {
                textChannel.sendMessage("usage = !kick <user> <reason>").queue();
            }
        } else {
            textChannel.sendMessage("Sorry, They forgot to give you that role").queue();

        }
    }
}