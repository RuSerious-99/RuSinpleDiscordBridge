package com.ruserious99.simplediscordbridge.util;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class MembersHelp {

    private SimpleDiscordBridge simpleDiscordBridge;

    public MembersHelp(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }


    public String getMemberAsId(String[] args, Guild guild) {
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[0])) {
                return m.getUser().getId();
            }
        }
        guild.getTextChannelById(simpleDiscordBridge.getConfigCommand().getGeneralTextChannel()).sendMessage(args[0] + " was not found ").queue();
        return null;
    }


    public Member getMemberasMember(String[] args, Guild guild) {
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[1])) {
                return m;
            }
        }
        guild.getTextChannelById(simpleDiscordBridge.getConfigCommand().getGeneralTextChannel()).sendMessage(args[1] + " was not found ").queue();
        return null;
    }
}
