package com.ruserious99.simplediscordbridge.discord_only_commands.commands;

import com.ruserious99.simplediscordbridge.discord_only_commands.type.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ICommand {

    @Override
    public void executeCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        message.delete().queue();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(0xb7c1ac);
        embedBuilder.setTitle("usage");
        embedBuilder.setDescription("**!help** - print this"
                + "\n **!clear <integer>**       - clears messages"
                + "\n **!ban <duration> <user>** - bans a user"
                + "\n **!unban <user by id>**    - unbans a user"
                + "\n **!kick <user> <reason>**  - kicks a user"
                + "\n **!ticketgui**  - creates ticket GUI"
                + "\n **!close** - deletes channel"
                + "\n **!reaction <guildId> <channelId> <messageId> <emote> <roleId>** - creates a reaction to give role"
                + "\n **!badword** <word> - adds a word to not permitted database"

        );
        member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue());
    }
}
