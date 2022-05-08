package com.ruserious99.simplediscordbridge.discord_only_commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.commands.ClearCommand;
import com.ruserious99.simplediscordbridge.discord_only_commands.commands.HelpCommand;
import com.ruserious99.simplediscordbridge.discord_only_commands.commands.JustHello;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {

    private final HelpCommand helpCommand;
    private final JustHello justHello;
    private final ClearCommand clearCommand;


    public CommandManager() {
        this.justHello = new JustHello();
        this.helpCommand = new HelpCommand();
        this.clearCommand = new ClearCommand();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(!e.getMember().getUser().isBot()){
            String[] args = e.getMessage().getContentRaw().split(" ");

            Guild guild = e.getGuild();
            Member member = e.getMember();
            TextChannel textChannel = e.getTextChannel();
            Message message = e.getMessage();


            switch(args[0]){
                case "!help":
                    this.helpCommand.executeCommand(args, guild, member, textChannel, message);
                    break;
                case "Hello":
                case "hello":
                    this.justHello.executeCommand(args, guild, member, textChannel, message);
                    break;
                case "!clear":
                    this.clearCommand.executeCommand(args, guild, member, textChannel, message);
                    break;
            }
        }
    }
}
