package com.ruserious99.simplediscordbridge.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class MembersHelp {

    public static String getMember(String[] args, Guild guild) {
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[0])) {
                return m.getUser().getId();
            }
        }
        guild.getTextChannelById(Const.GENERAL_TEXT_CHANNEL).sendMessage(args[0] + " was not found ").queue();
        return null;
    }


    public static Member getMemberasMember(String[] args, Guild guild) {
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[1])) {
                return m;
            }
        }
        guild.getTextChannelById(Const.GENERAL_TEXT_CHANNEL).sendMessage(args[1] + " was not found ").queue();
        return null;
    }


    public static boolean isMember(Guild guild, String member){
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(member)) {
                return true;
            }
        }
        return false;
    }
}
