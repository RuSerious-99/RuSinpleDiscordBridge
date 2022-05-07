package com.ruserious99.simplediscordbridge.commands;

import com.ruserious99.simplediscordbridge.util.Const;
import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
 import javax.security.auth.login.LoginException;

public class GiveRole implements CommandExecutor {

    private final SimpleDiscordBridge simpleDiscordBridge;

    public GiveRole(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Guild guild = simpleDiscordBridge.getJda().getGuildById(Const.GUILD_ID);
            if (guild != null) {
                try {
                    String member = getMember(args, guild);
                    String role   = getrole(args);
                    if(member != null) {
                        if (role != null) {
                            guild.addRoleToMember(member, guild.getRoleById(role)).queue();
                            guild.getTextChannelById(Const.GENERAL_TEXT_CHANNEL).sendMessage(args[1] + " role was given to " + args[0]).queue();
                            return true;
                        }
                    }
                } catch (LoginException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private String getMember(@NotNull String[] args, Guild guild) throws LoginException {

        for (Member m : guild.getMembers()) {
            if (m.getUser().getName().equals(args[0])) {
                return m.getUser().getId();
            }
        }
        guild.getTextChannelById(Const.GENERAL_TEXT_CHANNEL).sendMessage(args[0] + " was not found ").queue();
        return null;
    }

    private String getrole(@NotNull String[] args) {
        if (args[1].equalsIgnoreCase(Const.ADMIN)) {
            return Const.ADMIN_ID;
        }else if(args[1].equalsIgnoreCase(Const.MEMBER)){
            return Const.MEMBER_ID;
        }else if(args[1].equalsIgnoreCase(Const.HELPER)){
            return Const.HELPER_ID;
        }
        return null;
    }
}
