package com.ruserious99.simplediscordbridge.listeners;


import com.ruserious99.simplediscordbridge.database.DatabaseHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;

public class ButtonClick extends ListenerAdapter {


    DatabaseHandler databaseHandler = new DatabaseHandler();


    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (!event.getUser().isBot()) {
            if (!databaseHandler.hasTicket(Objects.requireNonNull(event.getMember()).getUser().getName())) {
                event.deferEdit().queue();

                switch (Objects.requireNonNull(event.getButton().getId())) {
                    case "unban" -> createUnbanRequest(event);
                    case "purchase" -> createPurchaseSupport(event);
                    case "bug" -> createBugReport(event);
                    case "other" -> allOthers(event);
                    default -> {
                    }
                }
            } else {
                event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Please wait for your current ticket to be resolved").queue());
            }
        }
    }

    private void allOthers(ButtonInteractionEvent event) {
        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getCategoryById("977949744464814142"))
                .createTextChannel("Ticket for - " + event.getUser().getName()).queue(textChannel -> {
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getMember())).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("977228925736456253")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("970434650470248468")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();


                    event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage
                            ("Success! Ticket created with id " + textChannel.getId()
                                    + "\n find your ticket in TICKET SUPPORT with your name").queue());

                    textChannel.sendMessage("Clearly state your issue "
                            + "\nadd names or any other info needed.\n"
                            + "\nWait for a Staff member to respond."
                    ).queue();

                    databaseHandler.addTicket(event.getMember().getUser().getName(), textChannel.getId());
                });
    }

    private void createBugReport(ButtonInteractionEvent event) {
        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getCategoryById("977949744464814142"))
                .createTextChannel("Bug-report - " + event.getUser().getName()).queue(textChannel -> {
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getMember())).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("977228925736456253")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("970434650470248468")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();


                    event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage
                            ("Success! Ticket created with id " + textChannel.getId()
                                    + "\n find your ticket in TICKET SUPPORT with your name").queue());

                    textChannel.sendMessage("Clearly state the bug you have found"
                            + "\nand a description if needed.\n"
                            + "\nWait for a Staff member to respond."
                    ).queue();

                    databaseHandler.addTicket(event.getMember().getUser().getName(), textChannel.getId());
                });
    }

    private void createPurchaseSupport(ButtonInteractionEvent event) {
        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getCategoryById("977949744464814142"))
                .createTextChannel("Purchase-Support - " + event.getUser().getName()).queue(textChannel -> {
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getMember())).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("977228925736456253")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("970434650470248468")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();


                    event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage
                            ("Success! Ticket created with id " + textChannel.getId()
                                    + "\n find your ticket in TICKET SUPPORT with your name").queue());

                    textChannel.sendMessage("Please state your issue with your puchase."
                            + "\nand state your purchase order number.\n"
                            + "\nWait for a Staff member to respond."
                    ).queue();

                    databaseHandler.addTicket(event.getMember().getUser().getName(), textChannel.getId());
                });
    }

    private void createUnbanRequest(ButtonInteractionEvent event) {
        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getCategoryById("977949744464814142"))
                .createTextChannel("Unban request - " + event.getUser().getName()).queue(textChannel -> {
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getMember())).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("977228925736456253")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("970434650470248468")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();


                    event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage
                            ("Success! Ticket created with id " + textChannel.getId()
                                    + "\n find your ticket in TICKET SUPPORT with your name").queue());

                    textChannel.sendMessage("Clearly state the members name to unban"
                            + "\nand the reason to unban them.\n"
                            + "\nWait for a Staff member to respond."
                    ).queue();

                    databaseHandler.addTicket(event.getMember().getUser().getName(), textChannel.getId());
                });
    }
}
