package com.ruserious99.simplediscordbridge.util;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class RolesHelp {

   private static SimpleDiscordBridge simpleDiscordBridge;

    public RolesHelp(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
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


   public static String getrole(String[] args) {
       if (args[1].equalsIgnoreCase(simpleDiscordBridge.getConfigCommand().getAdmin())) {
           return simpleDiscordBridge.getConfigCommand().getAdmin_Id();
       }else if(args[1].equalsIgnoreCase(simpleDiscordBridge.getConfigCommand().getMember())){
           return simpleDiscordBridge.getConfigCommand().getMemberId();
       }else if(args[1].equalsIgnoreCase(simpleDiscordBridge.getConfigCommand().getHelper())){
           return simpleDiscordBridge.getConfigCommand().getHelperId();
       }
       return null;
   }
    public static Boolean userHasRole(Member member, String role){
        List<Role> results = member.getRoles();
        for (Role r : results) {
            if (r.getName().equals(role)) {
                return true;
            }
        }
        return false;

    }
    public static Boolean userHasRole(Member member, String role1, String role2){
        if(userHasRole(member, role1)){
            return true;
        }else{
            return userHasRole(member, role2);
        }
    }
}



