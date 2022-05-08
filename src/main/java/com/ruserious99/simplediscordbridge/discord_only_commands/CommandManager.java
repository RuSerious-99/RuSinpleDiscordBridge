package com.ruserious99.simplediscordbridge.discord_only_commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.commands.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {

    private final HelpCommand    helpCommand;
    private final JustHello      justHello;
    private final ClearCommand   clearCommand;
    private final KickCommand    kickCommand;
    private final BanCommand     banCommand;
    private final UnBanCommand   unBanCommand;


    public CommandManager() {
        this.justHello      = new JustHello();
        this.helpCommand    = new HelpCommand();
        this.clearCommand   = new ClearCommand();
        this.kickCommand    = new KickCommand();
        this.banCommand     = new BanCommand();
        this.unBanCommand   = new UnBanCommand();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(!e.getMember().getUser().isBot()){
            String[] args = e.getMessage().getContentRaw().split(" ");

            Guild guild = e.getGuild();
            Member member = e.getMember();
            TextChannel textChannel = e.getTextChannel();
            Message message = e.getMessage();

            switch (args[0]) {
                case "!help" -> this.helpCommand.executeCommand(args, guild, member, textChannel, message);
                case "Hello", "hello" -> this.justHello.executeCommand(args, guild, member, textChannel, message);
                case "!clear" -> this.clearCommand.executeCommand(args, guild, member, textChannel, message);
                case "!kick" -> this.kickCommand.executeCommand(args, guild, member, textChannel, message);
                case "!ban" -> this.banCommand.executeCommand(args, guild, member, textChannel, message);
                case "!unban" -> this.unBanCommand.executeCommand(args, guild, member, textChannel, message);

            }
        }
    }
}