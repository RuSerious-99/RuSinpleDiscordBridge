package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import java.util.ArrayList;
import java.util.List;

public class TicketGuiCommand implements ICommand {

    private final SimpleDiscordBridge simpleDiscordBridge;

    public TicketGuiCommand(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        if(member.isOwner() || simpleDiscordBridge.getRolesHelp().hasRole(guild, member, simpleDiscordBridge.getConfigCommand().getAdmin_Id())) {
            if (message.getContentStripped().startsWith("!ticketgui")) {
                message.getChannel().sendMessage("""
                        Welcome to the Ticket System

                        Click one of the buttons below to open a ticket!
                        Please make sure to have any proof,
                        or anything else ready before making a ticket.
                        Once you open a ticket fill out the format and wait for a staff member to reply.""").setActionRow(getTicketButtons()).queue();
            }
        }
    }

    private static java.util.List<net.dv8tion.jda.api.interactions.components.buttons.Button> getTicketButtons(){
        List<net.dv8tion.jda.api.interactions.components.buttons.Button> buttons = new ArrayList<>();
        buttons.add(net.dv8tion.jda.api.interactions.components.buttons.Button.primary("unban", "Unban Appeal").withEmoji(Emoji.fromUnicode("U+1F6AB")));
        buttons.add(net.dv8tion.jda.api.interactions.components.buttons.Button.primary("purchase", "Purchases").withEmoji(Emoji.fromUnicode("U+1F4B1")));
        buttons.add(net.dv8tion.jda.api.interactions.components.buttons.Button.primary("bug", "Bug Report").withEmoji(Emoji.fromUnicode("U+1F41E")));
        buttons.add(Button.primary("other", "Other").withEmoji(Emoji.fromUnicode("U+2753")));

        return buttons;
    }

}
