package com.ruserious99.simplediscordbridge.listeners;

import com.ruserious99.simplediscordbridge.util.Const;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (RolesHelp.hasRole(e.getGuild(), e.getMember(), Const.ADMIN_ID)) {
            if (e.getChannel().getId().equals(Const.GENERAL_TEXT_CHANNEL)) {
                Bukkit.broadcastMessage(e.getMember().getUser().getAsTag()
                        + ": " + e.getMessage().getContentRaw());
            }
        }
    }
}
