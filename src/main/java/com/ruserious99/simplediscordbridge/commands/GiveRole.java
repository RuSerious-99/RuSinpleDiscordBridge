package com.ruserious99.simplediscordbridge.commands;

import com.ruserious99.simplediscordbridge.SimpleDiscordBridge;
import com.ruserious99.simplediscordbridge.util.MembersHelp;
import com.ruserious99.simplediscordbridge.util.RolesHelp;
import net.dv8tion.jda.api.entities.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GiveRole implements CommandExecutor {

    private final SimpleDiscordBridge simpleDiscordBridge;

    public GiveRole(SimpleDiscordBridge simpleDiscordBridge) {
        this.simpleDiscordBridge = simpleDiscordBridge;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Guild guild = simpleDiscordBridge.getJda().getGuildById(simpleDiscordBridge.getConfigCommand().getGuildId());
            if (guild != null) {
                String member = MembersHelp.getMemberAsId(args, guild);
                String role = RolesHelp.getrole(args);
                if (member != null) {
                    if (role != null) {
                        guild.addRoleToMember(member, Objects.requireNonNull(guild.getRoleById(role))).queue();
                        Objects.requireNonNull(guild.getTextChannelById(simpleDiscordBridge.getConfigCommand().getGeneralTextChannel())).sendMessage(args[1] + " role was given to " + args[0]).queue();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}