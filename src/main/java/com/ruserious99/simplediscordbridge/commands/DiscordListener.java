package com.ruserious99.simplediscordbridge.commands;

import com.ruserious99.simplediscordbridge.util.Const;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.util.List;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        Guild guild = e.getGuild();
        Role role = guild.getRoleById(Const.ADMIN_ID);
        List<Member> members = guild.getMembersWithRoles(role);

        for (Member m : members) {
            if (m.getUser().getName().equals(e.getMember().getUser().getName())) {
                if (e.getChannel().getId().equals(Const.GENERAL_TEXT_CHANNEL)) {
                    Bukkit.broadcastMessage(e.getMember().getUser().getAsTag()
                            + ": " + e.getMessage().getContentRaw());
                }
            }
        }
    }
}