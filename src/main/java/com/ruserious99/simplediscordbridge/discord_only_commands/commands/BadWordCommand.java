package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BadWordCommand implements ICommand {

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {

        message.delete().queue();
        if (args.length == 2) {
            if(RolesHelp.userHasRole(member, "Staff", "admin")){
                DatabaseHandler databaseHandler =  new DatabaseHandler();
                databaseHandler.addBadWord(args[1]);
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Success, you added bad word to database").queue());
            }else{
                member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Sorry, They forgot to give you that role").queue());
            }
        }else{
            member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("usage = !badword <word>").queue());
        }
    }
}
