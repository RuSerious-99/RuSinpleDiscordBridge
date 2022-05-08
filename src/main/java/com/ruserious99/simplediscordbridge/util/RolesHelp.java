package com.ruserious99.simplediscordbridge.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.util.List;

public class RolesHelp {

    public static String getrole(String[] args) {
        if (args[1].equalsIgnoreCase(Const.ADMIN)) {
            return Const.ADMIN_ID;
        }else if(args[1].equalsIgnoreCase(Const.MEMBER)){
            return Const.MEMBER_ID;
        }else if(args[1].equalsIgnoreCase(Const.HELPER)){
            return Const.HELPER_ID;
        }
        return null;
    }


    public static boolean hasRole(Guild guild, Member member, String roleCheck) {

        Role role = guild.getRoleById(roleCheck);
        List<Member> members = guild.getMembersWithRoles(role);

        for (Member m : members) {
            if (m.getUser().getName().equals(member.getUser().getName())) {
                return true;
            }
        }
        return false;
    }
}



