package com.ruserious99.simplediscordbridge.listeners;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.discord_only_commands.commands.TicketGuiCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Objects;

public class ButtonClick extends ListenerAdapter {

    public static final HashMap<String, String> hasOpenTicket = new HashMap<>();

    public void onButtonInteraction(ButtonInteractionEvent event) {
        System.out.println(hasOpenTicket.isEmpty());
        if (!event.getUser().isBot()) {
            if (!hasOpenTicket.containsValue(Objects.requireNonNull(event.getMember()).getUser().getName())) {
                event.deferEdit().queue();

                switch (Objects.requireNonNull(event.getButton().getId())) {
                    case "unban":
                        Objects.requireNonNull(event.getGuild()).createTextChannel("Unban request - " + event.getUser().getName()).queue(textChannel -> {
                            textChannel.createPermissionOverride(event.getMember()).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                            textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("977228925736456253"))).setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                            textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("970434650470248468"))).setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                            String id = textChannel.getId();
                            event.getMember().getUser().openPrivateChannel().queue(privateChannel -> {
                                privateChannel.sendMessage("Success! Ticket created with id " + id).queue();
                            });
                            hasOpenTicket.put(id, event.getMember().getUser().getName());
                        });
                        break;
                    case "purchase":
                        break;
                    case "bug":
                        break;
                    case "other":
                        break;
                    default:
                        break;

                }
            } else {
                event.getTextChannel().sendMessage("Please wait for your current ticket to be resolved").queue();
            }
        }
    }
}
