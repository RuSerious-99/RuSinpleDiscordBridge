package com.ruserious99.simplediscordbridge.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class MembersHelp {

    public static String getMemberAsId(String[] args, Guild guild) {
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[0])) {
                return m.getUser().getId();
            }
        }
        return null;
    }


    public static Member getMemberasMember(String[] args, Guild guild) {
        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[1])) {
                return m;
            }
        }
        return null;
    }
}
