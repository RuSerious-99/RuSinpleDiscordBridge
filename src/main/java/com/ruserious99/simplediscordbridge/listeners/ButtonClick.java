package com.ruserious99.simplediscordbridge.listeners;


import net.dv8tion.jda.api.EmbedBuilder;
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
                       createUnbanRequest(event);
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
                event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Please wait for your current ticket to be resolved").queue());
            }
        }
    }
    private void createUnbanRequest(ButtonInteractionEvent event){
        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getCategoryById("977949744464814142"))
                .createTextChannel("Unban request - " + event.getUser().getName()).queue(textChannel -> {
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getMember())).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("977228925736456253")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();
                    textChannel.createPermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("970434650470248468")))
                            .setAllow(Permission.VIEW_CHANNEL, Permission.ADMINISTRATOR).queue();


                    event.getMember().getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Success! Ticket created with id " + textChannel.getId()
                            + "\n **!clear <integer>**       - clears messages"
                            + "\n **!ban <duration> <user>** - bans a user"
                            + "\n **!unban <user by id>**    - unbans a user"
                            + "\n **!kick <user> <reason>**  - kicks a user"
                            + "\n **!ticketgui**  - creates ticket GUI"
                            + "\n **!clear_ticket**  - deletes channel").queue());


                    hasOpenTicket.put(textChannel.getId(), event.getMember().getUser().getName());
                });
    }
}
