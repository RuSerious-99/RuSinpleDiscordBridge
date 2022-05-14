package com.ruserious99.simplediscordbridge.util;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.util.List;

public class RolesHelp {

    private SimpleDiscordBridge simpleDiscordBridge;

    public RolesHelp(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    public String getrole(String[] args) {
        if (args[1].equalsIgnoreCase(simpleDiscordBridge.getConfigCommand().getAdmin())) {
            return simpleDiscordBridge.getConfigCommand().getAdmin_Id();
        }else if(args[1].equalsIgnoreCase(simpleDiscordBridge.getConfigCommand().getMember())){
            return simpleDiscordBridge.getConfigCommand().getMemberId();
        }else if(args[1].equalsIgnoreCase(simpleDiscordBridge.getConfigCommand().getHelper())){
            return simpleDiscordBridge.getConfigCommand().getHelperId();
        }
        return null;
    }


    public boolean hasRole(Guild guild, Member member, String roleCheck) {
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



