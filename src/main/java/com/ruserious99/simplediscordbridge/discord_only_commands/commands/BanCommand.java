package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;


public class BanCommand implements ICommand {

    private  SimpleDiscordBridge simpleDiscordBridge;

    public BanCommand(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        if (simpleDiscordBridge.getRolesHelp().hasRole(guild, member, simpleDiscordBridge.getConfigCommand().getAdmin_Id())) {
            if (args.length == 3) {
                Member target = simpleDiscordBridge.getMemberHelp().getMemberasMember(args, guild);
                if (target != null) {
                    target.ban(0, args[2]).queue();
                    member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Success: You Banned " + args[1] + " for " + args[2]).queue());
                }
            } else {
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("usage = !ban <user> <reason>").queue());
            }
        } else {
            member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Sorry, They forgot to give you that role").queue());
        }
    }
}
