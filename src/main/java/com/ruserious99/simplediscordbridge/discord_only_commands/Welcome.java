package com.ruserious99.simplediscordbridge.discord_only_commands;

import com.ruserious99.simplediscordbridge.util.Const;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Welcome extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e){

        if(!e.getMember().getUser().isBot()){
            TextChannel textChannel = e.getGuild().getSystemChannel();
            if(textChannel != null){
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(0xff4c30);
                embedBuilder.setDescription(e.getMember().getUser().getAsMention() + " Just joined the server. Welcome!");

                textChannel.sendMessageEmbeds(embedBuilder.build()).queue();

                Guild guild = e.getGuild();
                guild.addRoleToMember(e.getMember().getUser().getId(), guild.getRoleById(Const.MEMBER_ID)).queue();
            }
        }
    }
}
