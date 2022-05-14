package com.ruserious99.simplediscordbridge.commands;

import com.ruserious99.simplediscordbridge.config.ConfigCommand;
import com.ruserious99.simplediscordbridge.util.Const;
import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.util.MembersHelp;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.entities.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class RemoveRole implements CommandExecutor {

    private final SimpleDiscordBridge simpleDiscordBridge;

    public RemoveRole(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Guild guild = simpleDiscordBridge.getJda().getGuildById(simpleDiscordBridge.getConfigCommand().getGuildId());
            if (guild != null) {
                String member = simpleDiscordBridge.getMemberHelp().getMemberAsId(args, guild);
                String role   = simpleDiscordBridge.getRolesHelp().getrole(args);
                if(member != null) {
                    if (role != null) {
                        guild.removeRoleFromMember(member, guild.getRoleById(role)).queue();
                        guild.getTextChannelById(simpleDiscordBridge.getConfigCommand().getGeneralTextChannel()).sendMessage(args[1] + " role was removed from " + args[0]).queue();
                        return true;
                    }
                }
            }
        }
        return false;
    }


}

